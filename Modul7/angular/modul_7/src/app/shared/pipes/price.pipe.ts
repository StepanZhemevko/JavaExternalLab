import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'price',
})
export class PricePipe implements PipeTransform {
  signsMap = [
    {
      code: 'UAH',
      sign: '₴',
    },
    { code: 'USD', sign: '$' },
    { code: 'GBP', sign: '£' },
    { code: 'zloty', sign: 'zł' },
  ];
  transform(
    value: number | string,
    currency: string,
    signPosition: 'left' | 'right'
  ): string {
    
     return signPosition === 'left'
      ? `${this.getSignByCode(currency)} ${ value }`
      : `${ value } ${this.getSignByCode(currency)}`;
  }
  getSignByCode(currencyCode: string) {
    return this.signsMap.find((item) => item.code === currencyCode)?.sign;
  }
}
