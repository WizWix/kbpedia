export const apiFetch = async (url, options = {}) => {
  const defaultOptions = {
    credentials: 'include',
    headers: {'Content-Type': 'application/json'},
    ...options,
  };

  const response = await fetch(url, defaultOptions);
  if (response.status === 401) {
    window.dispatchEvent(new Event('auth-failure'));
  }
  return response;
};
