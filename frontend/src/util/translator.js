const CATEGORY_MAP = {
  'life': '라이프',
  'daily': '일상다반사',

};

export function Translate(str) {
  return CATEGORY_MAP[str];
}
