import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-booking-list',
  templateUrl: './user-booking-list.component.html',
  styleUrls: ['./user-booking-list.component.css']
})
export class UserBookingListComponent{

  closeResult = '';
  constructor(private modalService: NgbModal) {}

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
	}
	
}
