#!/usr/bin/env python3
"""Generates the bundled MinecraftBank resource pack into src/main/resources/pack/.

Everything here is drawn and synthesised in code, so the pack can be rebuilt without any
image or audio editor. To use your own art instead, drop replacement files into
plugins/MinecraftBank/resourcepack/ on the server (same paths as inside the pack); the
plugin merges them over the bundled files when it builds the pack.

Requires ffmpeg (libvorbis) for the sounds.
"""
import json
import math
import os
import random
import struct
import subprocess
import sys
import tempfile
import wave
import zlib

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "src", "main", "resources", "pack")


def hexc(h, a=255):
    h = h.lstrip("#")
    return (int(h[0:2], 16), int(h[2:4], 16), int(h[4:6], 16), a)


CLEAR = (0, 0, 0, 0)


class Canvas:
    def __init__(self, w=16, h=16):
        self.w, self.h = w, h
        self.p = [[CLEAR] * w for _ in range(h)]

    def px(self, x, y, c):
        if 0 <= x < self.w and 0 <= y < self.h:
            self.p[y][x] = c

    def get(self, x, y):
        return self.p[y][x] if 0 <= x < self.w and 0 <= y < self.h else CLEAR

    def rect(self, x0, y0, x1, y1, c):
        for y in range(y0, y1 + 1):
            for x in range(x0, x1 + 1):
                self.px(x, y, c)

    def circle(self, cx, cy, r, c):
        for y in range(self.h):
            for x in range(self.w):
                if (x + 0.5 - cx) ** 2 + (y + 0.5 - cy) ** 2 <= r * r:
                    self.px(x, y, c)

    def poly(self, pts, c):
        for y in range(self.h):
            for x in range(self.w):
                if inside(x + 0.5, y + 0.5, pts):
                    self.px(x, y, c)

    def line(self, pts, c):
        for (x0, y0), (x1, y1) in zip(pts, pts[1:]):
            n = max(abs(x1 - x0), abs(y1 - y0), 1)
            for i in range(n + 1):
                self.px(round(x0 + (x1 - x0) * i / n), round(y0 + (y1 - y0) * i / n), c)

    def outline(self, c):
        """Draws a 1px border around every opaque shape (the classic item-sprite look)."""
        add = []
        for y in range(self.h):
            for x in range(self.w):
                if self.p[y][x][3] == 0 and any(self.get(x + dx, y + dy)[3] > 0 for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1))):
                    add.append((x, y))
        for x, y in add:
            self.px(x, y, c)

    def scaled(self, k):
        out = Canvas(self.w * k, self.h * k)
        for y in range(out.h):
            for x in range(out.w):
                out.p[y][x] = self.p[y // k][x // k]
        return out

    def save(self, path):
        os.makedirs(os.path.dirname(path), exist_ok=True)
        raw = b"".join(b"\x00" + b"".join(bytes(c) for c in row) for row in self.p)

        def chunk(tag, data):
            return struct.pack(">I", len(data)) + tag + data + struct.pack(">I", zlib.crc32(tag + data) & 0xFFFFFFFF)

        png = b"\x89PNG\r\n\x1a\n" + chunk(b"IHDR", struct.pack(">IIBBBBB", self.w, self.h, 8, 6, 0, 0, 0))
        png += chunk(b"IDAT", zlib.compress(raw, 9)) + chunk(b"IEND", b"")
        with open(path, "wb") as f:
            f.write(png)


def inside(x, y, pts):
    hit = False
    for (x0, y0), (x1, y1) in zip(pts, pts[1:] + pts[:1]):
        if (y0 > y) != (y1 > y) and x < x0 + (y - y0) * (x1 - x0) / (y1 - y0):
            hit = not hit
    return hit


NAVY, NAVY_D, NAVY_L = hexc("#1d3f8a"), hexc("#0b1f45"), hexc("#3563c4")
GOLD, GOLD_D, GOLD_L = hexc("#e3b341"), hexc("#9a6a12"), hexc("#ffe08a")
WHITE, GRAY, GRAY_D = hexc("#f7f8fb"), hexc("#b9c0cc"), hexc("#6b7383")
RED, RED_D = hexc("#d94141"), hexc("#7a1717")
GREEN, GREEN_D, GREEN_L = hexc("#2e9e5b"), hexc("#175c33"), hexc("#7fd6a1")


def icon_back():
    c = Canvas()
    c.circle(8, 8, 7, NAVY)
    c.circle(7.4, 7.2, 4.8, NAVY_L)
    c.circle(8, 8, 4.8, NAVY)
    c.rect(6, 7, 11, 8, WHITE)
    for i in range(4):
        c.rect(3 + i, 7 - i, 3 + i, 8 + i, WHITE)
    c.outline(NAVY_D)
    return c


def icon_close():
    c = Canvas()
    c.circle(8, 8, 7, RED)
    for i in range(7):
        c.rect(4 + i, 4 + i, 5 + i, 4 + i, WHITE)
        c.rect(10 - i, 4 + i, 11 - i, 4 + i, WHITE)
    c.outline(RED_D)
    return c


def icon_coin():
    c = Canvas()
    c.circle(8, 8, 6.6, GOLD_D)
    c.circle(8, 8, 5.6, GOLD)
    c.circle(7, 7, 2.5, GOLD_L)
    c.circle(8, 8, 3.6, GOLD)
    c.rect(7, 7, 8, 8, GOLD_D)  # square hole, like an old mon coin
    c.px(4, 5, GOLD_L)
    c.px(5, 4, GOLD_L)
    c.outline(hexc("#5c3d05"))
    return c


def icon_gold_bars():
    c = Canvas()

    def bar(x, y):
        c.poly([(x + 1, y), (x + 6, y), (x + 7, y + 3), (x, y + 3)], GOLD)
        c.rect(x + 1, y, x + 5, y, GOLD_L)
        c.rect(x, y + 3, x + 7, y + 3, GOLD_D)

    bar(1, 10)
    bar(8, 10)
    bar(4, 6)
    c.outline(hexc("#5c3d05"))
    return c


def icon_banknote():
    c = Canvas()
    c.rect(2, 3, 14, 10, GREEN_D)
    c.rect(1, 5, 13, 12, GREEN)
    c.rect(2, 6, 12, 11, hexc("#38b56b"))
    c.circle(7.5, 8.5, 2.2, GREEN_L)
    c.px(7, 8, GREEN_D)
    c.px(7, 9, GREEN_D)
    c.rect(3, 7, 3, 7, GREEN_L)
    c.rect(11, 10, 11, 10, GREEN_L)
    c.outline(hexc("#0c3b1f"))
    return c


def icon_document():
    c = Canvas()
    c.poly([(3, 1), (10, 1), (13, 4), (13, 15), (3, 15)], WHITE)
    c.poly([(10, 1), (13, 4), (10, 4)], GRAY)
    for y in (6, 8, 10):
        c.rect(5, y, 11, y, GRAY)
    c.rect(5, 12, 8, 12, GRAY)
    c.circle(11, 12.5, 1.8, RED)
    c.outline(GRAY_D)
    return c


def icon_passbook():
    c = Canvas()
    c.rect(3, 2, 13, 14, WHITE)
    c.rect(2, 1, 12, 13, NAVY)
    c.rect(2, 1, 3, 13, NAVY_D)
    c.circle(7.8, 6.2, 2.4, GOLD)
    c.circle(7.8, 6.2, 1.2, GOLD_D)
    c.rect(5, 10, 10, 10, GOLD)
    c.outline(NAVY_D)
    return c


def icon_pen_form():
    c = icon_document()
    c.line([(14, 3), (8, 9)], GOLD_D)
    c.line([(15, 3), (9, 9)], GOLD)
    c.px(8, 10, hexc("#222222"))
    return c


def icon_star():
    c = Canvas()
    pts = []
    for i in range(10):
        r = 7.2 if i % 2 == 0 else 3.0
        a = -math.pi / 2 + i * math.pi / 5
        pts.append((8 + r * math.cos(a), 8.6 + r * math.sin(a)))
    c.poly(pts, GOLD)
    c.circle(7, 7, 1.6, GOLD_L)
    c.outline(hexc("#7a4f06"))
    return c


def icon_gem():
    c = Canvas()
    cyan, cyan_l, cyan_d = hexc("#46c7e0"), hexc("#bff2fb"), hexc("#1b7f99")
    c.poly([(4, 3), (12, 3), (15, 7), (8, 15), (1, 7)], cyan)
    c.poly([(4, 3), (8, 3), (6, 7), (1, 7)], cyan_l)
    c.poly([(8, 7), (15, 7), (8, 15)], cyan_d)
    c.rect(1, 7, 14, 7, cyan_l)
    c.outline(hexc("#0d4555"))
    return c


def icon_card():
    c = Canvas()
    c.rect(1, 4, 14, 12, NAVY)
    c.rect(1, 4, 14, 5, NAVY_L)
    for x, y in ((1, 4), (14, 4), (1, 12), (14, 12)):
        c.px(x, y, CLEAR)
    c.rect(3, 7, 5, 9, GOLD)
    c.px(4, 8, GOLD_D)
    c.rect(8, 10, 12, 10, GRAY)
    c.outline(NAVY_D)
    return c


def icon_chart():
    c = Canvas()
    c.rect(1, 2, 14, 13, WHITE)
    c.rect(3, 10, 4, 12, hexc("#c9d4ea"))
    c.rect(6, 8, 7, 12, hexc("#c9d4ea"))
    c.rect(9, 9, 10, 12, hexc("#c9d4ea"))
    c.rect(12, 5, 13, 12, hexc("#c9d4ea"))
    c.line([(2, 10), (5, 7), (8, 8), (13, 3)], GREEN)
    c.line([(2, 11), (5, 8), (8, 9), (13, 4)], GREEN_D)
    c.outline(GRAY_D)
    return c


def icon_panel():
    c = Canvas()
    base, dot = hexc("#132b57"), hexc("#18356a")
    c.rect(0, 0, 15, 15, base)
    for y in range(1, 16, 4):
        for x in range(1, 16, 4):
            c.px(x, y, dot)
    return c


def icon_panel_inner():
    c = Canvas()
    base, edge = hexc("#1c3a72"), hexc("#16305f")
    c.rect(0, 0, 15, 15, base)
    c.rect(0, 15, 15, 15, edge)
    c.rect(15, 0, 15, 15, edge)
    return c


def pack_icon():
    c = Canvas()
    c.rect(1, 1, 14, 14, GOLD)
    c.rect(1, 1, 14, 1, GOLD_L)
    c.rect(1, 14, 14, 14, GOLD_D)
    m = ["10001", "11011", "10101", "10001", "10001"]
    b = ["1110", "1001", "1110", "1001", "1110"]
    for y, row in enumerate(m):
        for x, v in enumerate(row):
            if v == "1":
                c.px(2 + x, 5 + y, NAVY_D)
    for y, row in enumerate(b):
        for x, v in enumerate(row):
            if v == "1":
                c.px(9 + x, 5 + y, NAVY_D)
    return c.scaled(8)


# material -> icon. Only flat vanilla items whose stock definition is a single model, so the
# fallback below reproduces vanilla exactly for every item that is not a MinecraftBank button.
ICONS = {
    "iron_door": ("back", icon_back),
    "barrier": ("close", icon_close),
    "gold_nugget": ("coin", icon_coin),
    "gold_ingot": ("gold_bars", icon_gold_bars),
    "emerald": ("banknote", icon_banknote),
    "paper": ("document", icon_document),
    "book": ("passbook", icon_passbook),
    "written_book": ("passbook", icon_passbook),
    "knowledge_book": ("passbook", icon_passbook),
    "writable_book": ("pen_form", icon_pen_form),
    "nether_star": ("star", icon_star),
    "diamond": ("gem", icon_gem),
    "name_tag": ("card", icon_card),
    "map": ("chart", icon_chart),
    "black_stained_glass_pane": ("panel", icon_panel),
    "gray_stained_glass_pane": ("panel_inner", icon_panel_inner),
}


# ---------------------------------------------------------------- sounds

RATE = 22050


def env_tone(freq, dur, amp=0.5, decay=6.0, partials=((1, 1.0),), attack=0.004):
    n = int(RATE * dur)
    out = []
    for i in range(n):
        t = i / RATE
        e = min(1.0, t / attack) * math.exp(-decay * t)
        out.append(amp * e * sum(a * math.sin(2 * math.pi * freq * m * t) for m, a in partials))
    return out


BELL = ((1, 1.0), (2.76, 0.35), (5.4, 0.12))
SQUAREISH = ((1, 1.0), (3, 0.3), (5, 0.12))
METAL = ((1, 1.0), (2.4, 0.6), (3.9, 0.4), (5.6, 0.25))


def mix(total, parts):
    buf = [0.0] * int(RATE * total)
    for start, samples in parts:
        s = int(RATE * start)
        for i, v in enumerate(samples):
            if s + i < len(buf):
                buf[s + i] += v
    peak = max(1e-9, max(abs(v) for v in buf))
    return [v * 0.7 / peak for v in buf]


def snd_click():
    rnd = random.Random(1)
    noise = [rnd.uniform(-1, 1) * math.exp(-180 * i / RATE) * 0.3 for i in range(int(RATE * 0.03))]
    return mix(0.06, [(0, env_tone(2600, 0.05, 0.5, 90)), (0, noise)])


def snd_menu():
    return mix(0.45, [(0, env_tone(1318.5, 0.3, 0.5, 9, BELL)), (0.09, env_tone(1975.5, 0.35, 0.45, 9, BELL))])


def snd_open():
    return mix(0.35, [(0, env_tone(784, 0.2, 0.4, 14, BELL)), (0.035, env_tone(1046.5, 0.2, 0.4, 14, BELL)), (0.07, env_tone(1318.5, 0.25, 0.4, 12, BELL))])


def snd_success():
    notes = [1046.5, 1318.5, 1568, 2093]
    return mix(0.75, [(i * 0.07, env_tone(f, 0.45, 0.45, 7, BELL)) for i, f in enumerate(notes)])


def snd_coin():
    return mix(0.5, [(0, env_tone(2400, 0.25, 0.5, 18, METAL)), (0.07, env_tone(2950, 0.35, 0.45, 12, METAL)), (0.07, env_tone(1975.5, 0.3, 0.2, 10, BELL))])


def snd_achievement():
    parts = [(i * 0.11, env_tone(f, 0.25, 0.35, 8, SQUAREISH)) for i, f in enumerate([523.25, 659.25, 783.99])]
    parts += [(0.33, env_tone(f, 0.9, 0.3, 3.2, SQUAREISH)) for f in (1046.5, 1318.5, 1568)]
    return mix(1.3, parts)


def snd_pickup():
    n = int(RATE * 0.08)
    out, phase = [], 0.0
    for i in range(n):
        t = i / RATE
        f = 900 - 600 * t / 0.08
        phase += 2 * math.pi * f / RATE
        out.append(0.6 * math.sin(phase) * math.exp(-30 * t) * min(1.0, t / 0.003))
    return mix(0.1, [(0, out)])


def snd_notify():
    return mix(0.7, [(0, env_tone(1568, 0.35, 0.5, 7, BELL)), (0.22, env_tone(1318.5, 0.45, 0.5, 6, BELL))])


def snd_error():
    return mix(0.3, [(0, env_tone(185, 0.1, 0.5, 12, SQUAREISH)), (0.13, env_tone(165, 0.14, 0.5, 10, SQUAREISH))])


SOUNDS = {
    "click": snd_click, "menu": snd_menu, "open": snd_open, "success": snd_success, "coin": snd_coin,
    "achievement": snd_achievement, "pickup": snd_pickup, "notify": snd_notify, "error": snd_error,
}


def write_ogg(samples, path):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with tempfile.TemporaryDirectory() as tmp:
        wav = os.path.join(tmp, "s.wav")
        with wave.open(wav, "wb") as w:
            w.setnchannels(1)
            w.setsampwidth(2)
            w.setframerate(RATE)
            w.writeframes(b"".join(struct.pack("<h", int(max(-1, min(1, v)) * 32767)) for v in samples))
        subprocess.run(["ffmpeg", "-y", "-loglevel", "error", "-i", wav, "-c:a", "libvorbis", "-q:a", "4", path], check=True)


def write_json(rel, data):
    path = os.path.join(ROOT, rel)
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        json.dump(data, f, indent=2, ensure_ascii=False)
        f.write("\n")


def main():
    write_json("pack.mcmeta", {"pack": {
        "description": "MinecraftBank 専用アイコン・効果音",
        "pack_format": 46, "supported_formats": [46, 999], "min_format": 46, "max_format": 999,
    }})
    pack_icon().save(os.path.join(ROOT, "pack.png"))
    drawn = set()
    for material, (icon, draw) in ICONS.items():
        if icon not in drawn:
            draw().save(os.path.join(ROOT, "assets/minecraftbank/textures/item/%s.png" % icon))
            model = {"parent": "minecraft:item/generated", "textures": {"layer0": "minecraftbank:item/" + icon}}
            if icon in ("panel", "panel_inner"):
                # fill the whole 18px slot so neighbouring filler panes join into one surface
                model["display"] = {"gui": {"scale": [1.125, 1.125, 1.125]}}
            write_json("assets/minecraftbank/models/item/%s.json" % icon, model)
            drawn.add(icon)
        write_json("assets/minecraft/items/%s.json" % material, {"model": {
            "type": "minecraft:select",
            "property": "minecraft:custom_model_data",
            "index": 0,
            "cases": [{"when": "mb:" + icon, "model": {"type": "minecraft:model", "model": "minecraftbank:item/" + icon}}],
            "fallback": {"type": "minecraft:model", "model": "minecraft:item/" + material},
        }})
    if "--no-sounds" not in sys.argv:
        for name, make in SOUNDS.items():
            write_ogg(make(), os.path.join(ROOT, "assets/minecraftbank/sounds/ui/%s.ogg" % name))
        write_json("assets/minecraftbank/sounds.json", {"ui." + n: {"sounds": ["minecraftbank:ui/" + n]} for n in SOUNDS})
    preview = Canvas(16 * len(drawn) + 2 * (len(drawn) - 1), 16)
    for i, icon in enumerate(sorted(drawn)):
        draw = next(d for _, (ic, d) in ICONS.items() if ic == icon)
        img = draw()
        for y in range(16):
            for x in range(16):
                preview.px(i * 18 + x, y, img.p[y][x])
    preview.scaled(4).save(os.path.join(os.path.dirname(os.path.abspath(__file__)), "preview.png"))


if __name__ == "__main__":
    main()
