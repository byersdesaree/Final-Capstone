import { Component, OnInit, OnDestroy } from '@angular/core';
import { ItemService } from '../item.service';
import { Item } from '../Item';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-items-master',
  templateUrl: './items-master.component.html',
  styleUrls: ['./items-master.component.scss']
})
export class ItemsMasterComponent implements OnInit, OnDestroy {
  items: Item[] = [];
  itemSub: Subscription;
  filterText: string = "";

  constructor(private itemsService: ItemService) { }

  ngOnInit() {
    this.getItemsFromServer();
  }

  ngOnDestroy() {
    if (this.itemSub) {
      this.itemSub.unsubscribe();
    }
  }

  getItemsFromServer() {
    this.itemSub = this.itemsService.getItems().subscribe(
      (res: Item[]) => {
        console.log("res:" + res);
        this.items = res;
      }, err => {
        console.log("err:" , err);
      }
    )
  }
}

