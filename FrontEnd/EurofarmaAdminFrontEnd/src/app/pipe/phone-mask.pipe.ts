import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'phoneMask',
  standalone: true
})
export class PhoneMaskPipe implements PipeTransform {

  transform(phoneNumber: string): string {
    if (!phoneNumber) return '';

  
    if (!phoneNumber.startsWith('+55')) {
      return phoneNumber; 
    }

   
    const cleaned = phoneNumber.replace(/\D/g, '');

  
    const match = cleaned.match(/^(\d{2})(\d{2})(\d{5})(\d{4})$/);

    if (match) {
      return `+${match[1]} (${match[2]}) ${match[3]}-${match[4]}`;
    }

   
    return phoneNumber;
  }
}
