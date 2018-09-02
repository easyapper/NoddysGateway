import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filter-container',
  templateUrl: './filter-container.component.html',
  styleUrls: ['./filter-container.component.css']
})
export class FilterContainerComponent implements OnInit {

  inputSpeedRange=[4,9];
  
  constructor() { }

  ngOnInit() {
  }

}
