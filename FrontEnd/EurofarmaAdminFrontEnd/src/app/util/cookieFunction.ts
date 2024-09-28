 function getCookie(name: string): string | null {
    if (typeof document !== 'undefined') {
      const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
      return match ? match[2] : null;
    }
    return null;
  }
  
 
   function deleteCookie(name: string): void {
    if (typeof document !== 'undefined') {
      document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/; secure; SameSite=Strict;`;
    }
  }

export {getCookie,deleteCookie};