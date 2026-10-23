// MinecraftBank web: shared chrome (header, navigation, footer, theme) and helpers used by every page.
// Loaded in <head> without defer so the saved theme applies before the first paint.
(function () {
  const store = {
    get(k) { try { return localStorage.getItem(k); } catch (e) { return null; } },
    set(k, v) { try { localStorage.setItem(k, v); } catch (e) { /* storage unavailable */ } },
  };
  const savedTheme = store.get('mb-theme');
  if (savedTheme === 'light' || savedTheme === 'dark') document.documentElement.dataset.theme = savedTheme;

  let token = new URLSearchParams(location.search).get('token') || '';
  try {
    if (token) sessionStorage.setItem('mb-token', token);
    else token = sessionStorage.getItem('mb-token') || '';
  } catch (e) { /* links just lose the token */ }

  const ICONS = {
    home: '<path d="M3 10.5 12 3l9 7.5"/><path d="M5 9.5V20h14V9.5"/><path d="M10 20v-6h4v6"/>',
    chart: '<path d="M3 3v18h18"/><path d="m7 15 4-4 3 3 6-7"/>',
    user: '<circle cx="12" cy="8" r="4"/><path d="M4 21c1.5-4 4.5-6 8-6s6.5 2 8 6"/>',
    book: '<path d="M4 4.5A2.5 2.5 0 0 1 6.5 2H20v17H6.5A2.5 2.5 0 0 0 4 21.5z"/><path d="M4 4.5v17"/><path d="M9 7h7M9 11h5"/>',
    bank: '<path d="M3 10h18L12 4z"/><path d="M5 10v8M9.5 10v8M14.5 10v8M19 10v8"/><path d="M3 21h18"/>',
    coin: '<circle cx="12" cy="12" r="9"/><path d="M14.8 9.2c-.6-1-1.7-1.5-2.8-1.5-1.6 0-2.8.9-2.8 2.2 0 3.1 5.8 1.6 5.8 4.4 0 1.3-1.3 2.2-3 2.2-1.2 0-2.4-.6-3-1.6M12 6v1.7M12 16.5V18"/>',
    clock: '<circle cx="12" cy="12" r="9"/><path d="M12 7v5l3 2"/>',
    shield: '<path d="M12 3 4 6v6c0 4.5 3.4 8 8 9 4.6-1 8-4.5 8-9V6z"/><path d="m9 12 2 2 4-4"/>',
    alert: '<path d="M12 3 2 20h20z"/><path d="M12 10v4M12 17h.01"/>',
    check: '<circle cx="12" cy="12" r="9"/><path d="m8 12 3 3 5-6"/>',
    x: '<circle cx="12" cy="12" r="9"/><path d="m9 9 6 6M15 9l-6 6"/>',
    search: '<circle cx="11" cy="11" r="7"/><path d="m20 20-4-4"/>',
    sun: '<circle cx="12" cy="12" r="4"/><path d="M12 2v2M12 20v2M4.9 4.9l1.4 1.4M17.7 17.7l1.4 1.4M2 12h2M20 12h2M4.9 19.1l1.4-1.4M17.7 6.3l1.4-1.4"/>',
    moon: '<path d="M20 14.5A8 8 0 1 1 9.5 4a6.5 6.5 0 0 0 10.5 10.5z"/>',
    gift: '<rect x="3" y="8" width="18" height="4" rx="1"/><path d="M5 12v9h14v-9M12 8v13"/><path d="M12 8c-1.5-3-5-3.5-5-1.2C7 8 9 8 12 8c3 0 5 0 5-1.2C17 4.5 13.5 5 12 8z"/>',
    map: '<path d="m9 4-6 2.5v13.5l6-2.5 6 2.5 6-2.5V4l-6 2.5z"/><path d="M9 4v13.5M15 6.5V20"/>',
    box: '<path d="M3 7.5 12 3l9 4.5v9L12 21l-9-4.5z"/><path d="m3 7.5 9 4.5 9-4.5M12 12v9"/>',
    card: '<rect x="2.5" y="5" width="19" height="14" rx="2.5"/><path d="M2.5 10h19M6 15h4"/>',
    store: '<path d="M4 9h16l-1.5-5h-13z"/><path d="M5 9v11h14V9"/><path d="M9.5 20v-5h5v5"/>',
    globe: '<circle cx="12" cy="12" r="9"/><path d="M3 12h18M12 3c2.5 2.7 3.6 5.7 3.6 9s-1.1 6.3-3.6 9c-2.5-2.7-3.6-5.7-3.6-9S9.5 5.7 12 3z"/>',
    gavel: '<path d="m14 4 6 6M11.5 6.5l6 6M13 5l-6 6 3 3 6-6"/><path d="m8.5 12.5-5 5 3 3 5-5"/>',
    target: '<circle cx="12" cy="12" r="9"/><circle cx="12" cy="12" r="5"/><circle cx="12" cy="12" r="1"/>',
    wallet: '<path d="M3 7a2 2 0 0 1 2-2h13v4"/><path d="M3 7v11a2 2 0 0 0 2 2h15V9H5a2 2 0 0 1-2-2z"/><circle cx="16" cy="14.5" r="1.2"/>',
    inbox: '<path d="M3 13h5l1.5 3h5L16 13h5"/><path d="M5 5h14l2 8v6H3v-6z"/>',
    send: '<path d="M21 3 10 14"/><path d="M21 3 14 21l-4-7-7-4z"/>',
    calendar: '<rect x="3" y="5" width="18" height="16" rx="2"/><path d="M3 10h18M8 3v4M16 3v4"/>',
    repeat: '<path d="M17 2l3 3-3 3"/><path d="M4 11V9a4 4 0 0 1 4-4h12"/><path d="M7 22l-3-3 3-3"/><path d="M20 13v2a4 4 0 0 1-4 4H4"/>',
    lock: '<rect x="4" y="10" width="16" height="11" rx="2"/><path d="M8 10V7a4 4 0 0 1 8 0v3"/>',
    info: '<circle cx="12" cy="12" r="9"/><path d="M12 11v6M12 7.5h.01"/>',
    trophy: '<path d="M8 4h8v5a4 4 0 0 1-8 0z"/><path d="M8 6H4v1a4 4 0 0 0 4 4M16 6h4v1a4 4 0 0 1-4 4M12 13v4M8 21h8M9.5 17h5"/>',
    receipt: '<path d="M6 2h12v20l-3-2-3 2-3-2-3 2z"/><path d="M9 7h6M9 11h6M9 15h4"/>',
    trend: '<path d="m3 17 6-6 4 4 8-8"/><path d="M15 7h6v6"/>',
    pulse: '<path d="M3 12h4l3-8 4 16 3-8h4"/>',
    flag: '<path d="M5 21V4M5 4h11l-2 4 2 4H5"/>',
  };

  function icon(name, cls) {
    const ns = 'http://www.w3.org/2000/svg';
    const svg = document.createElementNS(ns, 'svg');
    svg.setAttribute('viewBox', '0 0 24 24');
    svg.setAttribute('class', 'i' + (cls ? ' ' + cls : ''));
    svg.setAttribute('aria-hidden', 'true');
    svg.innerHTML = ICONS[name] || ICONS.info;
    return svg;
  }

  /** el('div', {class: 'x', onclick: fn}, 'text', childNode, ...) - text children are always set as text, never HTML. */
  function el(tag, attrs, ...children) {
    const e = document.createElement(tag);
    for (const [k, v] of Object.entries(attrs || {})) {
      if (v === null || v === undefined || v === false) continue;
      if (k.startsWith('on') && typeof v === 'function') e.addEventListener(k.slice(2), v);
      else if (k === 'style' && typeof v === 'object') Object.assign(e.style, v);
      else e.setAttribute(k, v === true ? '' : v);
    }
    for (const c of children.flat()) {
      if (c === null || c === undefined || c === false) continue;
      e.append(c instanceof Node ? c : document.createTextNode(String(c)));
    }
    return e;
  }

  const nf = new Intl.NumberFormat('ja-JP', { maximumFractionDigits: 0 });
  const nf2 = new Intl.NumberFormat('ja-JP', { maximumFractionDigits: 2 });
  const fmt = {
    yen: (n) => nf.format(Math.round(Number(n) || 0)) + '円',
    num: (n) => nf.format(Math.round(Number(n) || 0)),
    dec: (n) => nf2.format(Number(n) || 0),
    pct: (n, digits = 1) => ((n > 0 ? '+' : n < 0 ? '−' : '±') + Math.abs(n).toFixed(digits) + '%'),
    compact(n) {
      const v = Math.abs(Number(n) || 0), s = n < 0 ? '−' : '';
      if (v >= 1e8) return s + nf2.format(v / 1e8) + '億';
      if (v >= 1e4) return s + nf2.format(v / 1e4) + '万';
      return s + nf.format(v);
    },
    duration(ms) {
      if (!ms || ms <= 0) return 'まもなく';
      const min = Math.floor(ms / 60000), d = Math.floor(min / 1440), h = Math.floor((min % 1440) / 60), m = min % 60;
      if (d > 0) return d + '日' + h + '時間';
      if (h > 0) return h + '時間' + m + '分';
      return Math.max(1, m) + '分';
    },
    time: (ms) => new Date(ms).toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit' }),
    dateTime(ms) {
      const d = new Date(ms);
      return `${d.getMonth() + 1}/${d.getDate()} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}`;
    },
  };

  /** Sets text and drops the loading skeleton. */
  function fill(target, text) {
    const e = typeof target === 'string' ? document.getElementById(target) : target;
    if (!e) return;
    e.textContent = text;
    e.classList.remove('skeleton');
  }

  /** Animates a number from its last shown value; the final text is always exactly format(value). */
  function countUp(target, value, format) {
    const e = typeof target === 'string' ? document.getElementById(target) : target;
    if (!e) return;
    const from = Number(e.dataset.v || 0);
    e.dataset.v = value;
    e.classList.remove('skeleton');
    if (matchMedia('(prefers-reduced-motion: reduce)').matches || from === value) { e.textContent = format(value); return; }
    const start = performance.now(), dur = 700;
    const step = (now) => {
      const t = Math.min(1, (now - start) / dur), k = 1 - Math.pow(1 - t, 3);
      e.textContent = format(t < 1 ? from + (value - from) * k : value);
      if (t < 1) requestAnimationFrame(step);
    };
    requestAnimationFrame(step);
  }

  function toast(ok, text) {
    let box = document.querySelector('.toasts');
    if (!box) { box = el('div', { class: 'toasts', role: 'status', 'aria-live': 'polite' }); document.body.append(box); }
    const t = el('div', { class: 'toast ' + (ok ? 'ok' : 'err') }, icon(ok ? 'check' : 'alert'), el('div', {}, text));
    box.append(t);
    setTimeout(() => t.remove(), 5000);
  }

  function empty(text, iconName) {
    return el('div', { class: 'empty' }, icon(iconName || 'inbox'), text);
  }

  const SVGNS = 'http://www.w3.org/2000/svg';
  function svgEl(tag, attrs) {
    const e = document.createElementNS(SVGNS, tag);
    for (const [k, v] of Object.entries(attrs || {})) e.setAttribute(k, v);
    return e;
  }

  function niceTicks(lo, hi, count) {
    const span = hi - lo || 1, raw = span / count, mag = Math.pow(10, Math.floor(Math.log10(raw)));
    const step = [1, 2, 2.5, 5, 10].map((m) => m * mag).find((s) => s >= raw) || raw;
    const ticks = [];
    for (let v = Math.ceil(lo / step) * step; v <= hi + step * 1e-9; v += step) ticks.push(v);
    return ticks;
  }

  let chartSeq = 0;
  /**
   * Single-series line+area chart with crosshair tooltip.
   * opts: { points: [{t, v}], height, format(v), formatTime(t), label }
   */
  function lineChart(container, opts) {
    container.replaceChildren();
    container.classList.add('chart');
    const pts = (opts.points || []).filter((p) => Number.isFinite(p.v));
    const H = opts.height || 240;
    if (pts.length < 2) {
      container.style.height = H + 'px';
      container.append(el('div', { class: 'chart-empty' }, opts.emptyText || 'データが集まるとグラフを表示します'));
      return;
    }
    container.style.height = '';
    const W = Math.max(280, container.clientWidth || 600);
    const fmtV = opts.format || fmt.num, fmtT = opts.formatTime || fmt.dateTime;
    let lo = Math.min(...pts.map((p) => p.v)), hi = Math.max(...pts.map((p) => p.v));
    if (lo === hi) { lo -= Math.max(1, Math.abs(lo) * 0.05); hi += Math.max(1, Math.abs(hi) * 0.05); }
    const pad = (hi - lo) * 0.12;
    lo -= pad; hi += pad;
    const ticks = niceTicks(lo, hi, 4);
    lo = Math.min(lo, ticks[0]); hi = Math.max(hi, ticks[ticks.length - 1]);
    const labelW = Math.max(...ticks.map((t) => fmt.compact(t).length)) * 7 + 14;
    const L = labelW, R = 10, T = 10, B = 26;
    const x = (i) => L + (i / (pts.length - 1)) * (W - L - R);
    const y = (v) => T + (1 - (v - lo) / (hi - lo)) * (H - T - B);
    const svg = svgEl('svg', { viewBox: `0 0 ${W} ${H}`, height: H, role: 'img', 'aria-label': opts.label || 'グラフ' });
    const id = 'g' + (++chartSeq);
    const defs = svgEl('defs');
    const grad = svgEl('linearGradient', { id, x1: 0, y1: 0, x2: 0, y2: 1 });
    grad.append(svgEl('stop', { offset: '0%', 'stop-color': 'var(--series-1)', 'stop-opacity': 0.22 }));
    grad.append(svgEl('stop', { offset: '100%', 'stop-color': 'var(--series-1)', 'stop-opacity': 0 }));
    defs.append(grad);
    svg.append(defs);
    for (const t of ticks) {
      svg.append(svgEl('line', { class: 'gridline', x1: L, x2: W - R, y1: y(t), y2: y(t) }));
      const lab = svgEl('text', { class: 'axis', x: L - 8, y: y(t) + 4, 'text-anchor': 'end' });
      lab.textContent = fmt.compact(t);
      svg.append(lab);
    }
    const idxs = pts.length > 2 ? [0, Math.floor((pts.length - 1) / 2), pts.length - 1] : [0, pts.length - 1];
    for (const i of idxs) {
      const lab = svgEl('text', { class: 'axis', x: x(i), y: H - 6, 'text-anchor': i === 0 ? 'start' : i === pts.length - 1 ? 'end' : 'middle' });
      lab.textContent = fmtT(pts[i].t);
      svg.append(lab);
    }
    const line = pts.map((p, i) => `${i ? 'L' : 'M'}${x(i).toFixed(1)},${y(p.v).toFixed(1)}`).join(' ');
    svg.append(svgEl('path', { d: `${line} L${x(pts.length - 1)},${H - B} L${x(0)},${H - B} Z`, fill: `url(#${id})` }));
    svg.append(svgEl('path', { d: line, fill: 'none', stroke: 'var(--series-1)', 'stroke-width': 2, 'stroke-linejoin': 'round', 'stroke-linecap': 'round' }));
    const last = pts[pts.length - 1];
    svg.append(svgEl('circle', { cx: x(pts.length - 1), cy: y(last.v), r: 4, fill: 'var(--series-1)', stroke: 'var(--surface)', 'stroke-width': 2 }));
    const cross = svgEl('line', { y1: T, y2: H - B, stroke: 'var(--ink-3)', 'stroke-width': 1, 'stroke-dasharray': '3 3', visibility: 'hidden' });
    const dot = svgEl('circle', { r: 5, fill: 'var(--series-1)', stroke: 'var(--surface)', 'stroke-width': 2, visibility: 'hidden' });
    const hit = svgEl('rect', { x: L, y: 0, width: W - L - R, height: H - B, fill: 'transparent' });
    svg.append(cross, dot, hit);
    const tip = el('div', { class: 'chart-tip' });
    container.append(svg, tip);
    const show = (i) => {
      cross.setAttribute('x1', x(i)); cross.setAttribute('x2', x(i)); cross.setAttribute('visibility', 'visible');
      dot.setAttribute('cx', x(i)); dot.setAttribute('cy', y(pts[i].v)); dot.setAttribute('visibility', 'visible');
      tip.replaceChildren(el('div', { class: 't' }, fmtT(pts[i].t)), el('div', { class: 'v' }, fmtV(pts[i].v)));
      tip.style.display = 'block';
      const rect = svg.getBoundingClientRect(), sx = rect.width / W;
      let left = x(i) * sx + 14;
      if (left + tip.offsetWidth > rect.width) left = x(i) * sx - tip.offsetWidth - 14;
      tip.style.left = Math.max(0, left) + 'px';
      tip.style.top = Math.max(0, y(pts[i].v) * sx - tip.offsetHeight - 10) + 'px';
    };
    const hide = () => { tip.style.display = 'none'; cross.setAttribute('visibility', 'hidden'); dot.setAttribute('visibility', 'hidden'); };
    hit.addEventListener('pointermove', (ev) => {
      const rect = svg.getBoundingClientRect();
      const px = (ev.clientX - rect.left) * (W / rect.width);
      show(Math.max(0, Math.min(pts.length - 1, Math.round(((px - L) / (W - L - R)) * (pts.length - 1)))));
    });
    hit.addEventListener('pointerleave', hide);
  }

  function sparkline(values, opts) {
    const w = 160, h = 38, p = 3;
    const svg = svgEl('svg', { viewBox: `0 0 ${w} ${h}`, preserveAspectRatio: 'none', 'aria-hidden': 'true' });
    if (!values || values.length < 2) return svg;
    const min = Math.min(...values), max = Math.max(...values), span = max - min || 1;
    const pts = values.map((v, i) => [p + (i / (values.length - 1)) * (w - p * 2), h - p - ((v - min) / span) * (h - p * 2)]);
    const d = pts.map((q, i) => `${i ? 'L' : 'M'}${q[0].toFixed(1)},${q[1].toFixed(1)}`).join(' ');
    const color = (opts && opts.color) || 'var(--series-1)';
    svg.append(svgEl('path', { d: `${d} L${w - p},${h} L${p},${h} Z`, fill: color, opacity: 0.1 }));
    svg.append(svgEl('path', { d, fill: 'none', stroke: color, 'stroke-width': 2, 'vector-effect': 'non-scaling-stroke', 'stroke-linejoin': 'round' }));
    return svg;
  }

  function delta(pctValue) {
    const cls = pctValue > 0.05 ? 'up' : pctValue < -0.05 ? 'down' : 'flat';
    const arrow = cls === 'up' ? '▲ ' : cls === 'down' ? '▼ ' : '';
    return el('span', { class: 'delta ' + cls }, arrow + (cls === 'flat' ? '±' + Math.abs(pctValue).toFixed(2) + '%' : fmt.pct(pctValue, 2)));
  }

  /** Blue <-> gray <-> red, clamped at ±range %. */
  function diverging(pctValue, range = 30) {
    const t = Math.max(-1, Math.min(1, (pctValue || 0) / range));
    return `color-mix(in srgb, ${t < 0 ? 'var(--div-neg)' : 'var(--div-pos)'} ${Math.round(Math.abs(t) * 100)}%, var(--div-mid))`;
  }

  // ---------- chrome ----------
  function buildChrome() {
    const q = token ? '?token=' + encodeURIComponent(token) : '';
    const links = [['home', '/', 'ホーム', 'home'], ['market', '/economy.html', 'マーケット', 'chart']];
    if (token) links.push(['me', '/me.html' + q, 'マイページ', 'user'], ['book', '/me-plus.html' + q, '家計簿', 'book']);
    const current = document.body.dataset.page || '';

    const clock = el('b', { class: 'num' });
    const tick = () => { clock.textContent = new Date().toLocaleString('ja-JP', { month: 'numeric', day: 'numeric', weekday: 'short', hour: '2-digit', minute: '2-digit' }); };
    tick();
    setInterval(tick, 15000);
    const topbar = el('div', { class: 'topbar' }, el('div', { class: 'container' },
      el('span', {}, 'サーバー内銀行 ・ 24時間 365日営業'),
      el('span', { class: 'hide-sm' }, '現在 ', clock)));

    const emblem = el('span', { class: 'brand-emblem' });
    emblem.innerHTML = '<svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2.5 2.5 8v1.8h19V8zM4.5 11h2.6v7H4.5zm4.6 0h2.6v7H9.1zm4.6 0h2.6v7h-2.6zm4.6 0h2.6v7h-2.6zM2.5 19.3h19v2.2h-19z"/></svg>';
    const brand = el('a', { class: 'brand', href: '/' }, emblem,
      el('span', { class: 'brand-text' }, el('strong', {}, 'MinecraftBank'), el('small', {}, 'マインクラフト銀行')));
    const nav = el('nav', { class: 'nav', 'aria-label': 'メインメニュー' },
      links.map(([id, href, label, ic]) => el('a', { href, 'aria-current': id === current ? 'page' : null }, icon(ic), label)));

    const themeBtn = el('button', { class: 'icon-btn', type: 'button' });
    const paintThemeBtn = () => {
      const dark = document.documentElement.dataset.theme === 'dark'
        || (!document.documentElement.dataset.theme && matchMedia('(prefers-color-scheme: dark)').matches);
      themeBtn.replaceChildren(icon(dark ? 'sun' : 'moon'));
      themeBtn.setAttribute('aria-label', dark ? 'ライトモードにする' : 'ダークモードにする');
      themeBtn.title = themeBtn.getAttribute('aria-label');
    };
    themeBtn.addEventListener('click', () => {
      const dark = themeBtn.getAttribute('aria-label') === 'ライトモードにする';
      document.documentElement.dataset.theme = dark ? 'light' : 'dark';
      store.set('mb-theme', document.documentElement.dataset.theme);
      paintThemeBtn();
      document.dispatchEvent(new CustomEvent('mb:theme'));
    });
    paintThemeBtn();

    const masthead = el('header', { class: 'masthead' }, el('div', { class: 'container' }, brand, nav, themeBtn));
    document.body.prepend(topbar, masthead);

    const footer = el('footer', { class: 'site-footer' }, el('div', { class: 'container' },
      el('div', {},
        el('h4', {}, 'MinecraftBank マインクラフト銀行'),
        el('p', {}, 'サーバーの経済をまるごと見える化。預金・投資・オークション・税金まで、すべてゲーム内通貨で動いています。')),
      el('div', {}, el('h4', {}, 'ページ'), links.map(([, href, label]) => el('a', { href }, label))),
      el('div', {}, el('h4', {}, 'ご利用にあたって'),
        el('p', {}, 'マイページのリンクはあなた専用です。他の人に教えないでください。'),
        el('p', { style: { marginTop: '6px' } }, 'リンクはゲーム内で /meco webpage を実行すると発行されます。')),
      el('div', { class: 'legal' }, el('span', {}, '表示金額はすべてゲーム内通貨です。'), el('span', {}, '© MinecraftBank'))));
    document.body.append(footer);
  }

  window.MB = { el, icon, fmt, fill, countUp, toast, empty, lineChart, sparkline, delta, diverging, token, store };
  if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', buildChrome);
  else buildChrome();
})();
