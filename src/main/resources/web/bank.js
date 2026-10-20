// Shared header, navigation and footer for every MinecraftBank web page.
(function () {
  let token = new URLSearchParams(location.search).get('token') || '';
  try {
    if (token) sessionStorage.setItem('mb-token', token);
    else token = sessionStorage.getItem('mb-token') || '';
  } catch (e) { /* storage unavailable: links just lose the token */ }

  const q = token ? '?token=' + encodeURIComponent(token) : '';
  const links = [['home', '/', 'ホーム'], ['market', '/economy.html', 'マーケット']];
  if (token) links.push(['me', '/me.html' + q, 'マイページ'], ['book', '/me-plus.html' + q, '家計簿']);
  const current = document.body.dataset.page || '';

  const el = (tag, cls, text) => {
    const e = document.createElement(tag);
    if (cls) e.className = cls;
    if (text !== undefined) e.textContent = text;
    return e;
  };

  const strip = el('div', 'site-strip');
  const stripInner = el('div', 'site-inner');
  stripInner.append(el('span', '', 'サーバー内銀行 ・ 24時間営業'), el('span', '', '表示金額はすべてゲーム内通貨です'));
  strip.append(stripInner);

  const bar = el('div', 'site-bar');
  const inner = el('div', 'site-inner');
  const brand = el('a', 'brand');
  brand.href = '/';
  const name = el('span', 'brand-name', 'MinecraftBank');
  name.append(el('small', '', 'マインクラフト銀行'));
  brand.append(el('span', 'brand-mark', 'MB'), name);
  const nav = el('nav', 'site-nav');
  nav.setAttribute('aria-label', 'サイト内メニュー');
  for (const [id, href, label] of links) {
    const a = el('a', '', label);
    a.href = href;
    if (id === current) a.setAttribute('aria-current', 'page');
    nav.append(a);
  }
  inner.append(brand, nav);
  bar.append(inner);
  document.body.prepend(strip, bar);

  const footer = el('footer', 'site-footer');
  const fInner = el('div', 'site-inner');
  const left = el('span');
  left.append(el('b', '', 'MinecraftBank'), document.createTextNode(' ・ マインクラフト銀行'));
  fInner.append(left, el('span', '', '個人ページのリンクは他人に教えないでください。'));
  footer.append(fInner);
  document.body.append(footer);
})();
