import { Component, OnInit, HostListener } from '@angular/core';
import { DataService } from 'src/app/shared/services/data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  coupons: any[] = [];
  page = 1;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.fetchData();
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
      this.page++;
      this.fetchData();
    }
  }

  fetchData() {
    this.dataService.getDataByService(this.page).subscribe((res: any) => {
      this.coupons.push(...res);
    });
  }

}
