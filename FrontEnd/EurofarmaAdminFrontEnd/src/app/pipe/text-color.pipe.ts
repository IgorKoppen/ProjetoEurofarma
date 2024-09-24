import { Pipe, PipeTransform } from '@angular/core';


function hexToRgb(hex: string): { r: number, g: number, b: number } | null {
  hex = hex.replace(/^#/, '');
  
  if (hex.length === 3) {
    hex = hex.split('').map(c => c + c).join('');
  }

  const bigint = parseInt(hex, 16);
  if (isNaN(bigint)) return null;

  return {
    r: (bigint >> 16) & 255,
    g: (bigint >> 8) & 255,
    b: bigint & 255
  };
}


function luminance(r: number, g: number, b: number): number {
  const a = [r, g, b].map(function (v) {
    v /= 255;
    return v <= 0.03928 ? v / 12.92 : Math.pow((v + 0.055) / 1.055, 2.4);
  });
  return a[0] * 0.2126 + a[1] * 0.7152 + a[2] * 0.0722;
}

@Pipe({
  name: 'textColor',
  standalone: true
})
export class TextColorPipe implements PipeTransform {


  transform(bgColor: string): string {
    const rgb = hexToRgb(bgColor);
    if (!rgb) return '#000'; 
    const luminanceValue = luminance(rgb.r, rgb.g, rgb.b);
    return luminanceValue > 0.5 ? '#000' : '#FFF';
  }
}