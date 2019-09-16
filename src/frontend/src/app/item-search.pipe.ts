import { Pipe, PipeTransform } from '@angular/core';
import {Item} from './item';

@Pipe({
  name: 'itemSearch'
})
export class ItemSearchPipe implements PipeTransform {

  transform(value: Item[], searchTerm: string): Item[] {
    return value.filter(v => v.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }

}
