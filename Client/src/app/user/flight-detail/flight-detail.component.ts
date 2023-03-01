import { Component, ComponentFactoryResolver, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ApiService } from "src/app/api.service";
import { DatePipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
import { of, VirtualTimeScheduler } from "rxjs";
import { ThisReceiver } from "@angular/compiler";
interface Seat {
  seatId: string;
  seatType: string;
  price: number;
  number: any;
}
@Component({
  selector: "app-flight-detail",
  templateUrl: "./flight-detail.component.html",
  styleUrls: ["./flight-detail.component.css"],
})
export class FlightDetailComponent{
  seatIds!: any[];
  items: any;
  selectedSeatPrice!: number;
  depDateTime: any;
  selectedSeatQuantity!:number;
  flightId: any;
  randomFlights: any;
  seatBookingFormArray: any;
  destDateTime: any;
  seats!: any[];
  depTime: any;
  seatPrice: any;
  seatList!: any[];
  myForm!: FormGroup;
  destTime: any;
  seatdetail: any;
  quantityControl!:FormGroup
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {}

  getRandom() {
    this.apiService.getTwoRandom().subscribe({
      next: (response: any) => {
        console.log("RANDOM FLIGHTS ARE : ");
        this.randomFlights = response;
        console.log(this.randomFlights);
      },
    });
  }
  ngOnInit() {
   
    this.getRandom();
    this.route.params.subscribe((params) => {
          const flightId = params["flightId"];
          this.flightId = flightId;
    this.apiService.getFlightDetail(flightId).subscribe({
            next: (response: any) => {
              this.items = response;
              this.seatdetail = this.items.seats;
              this.myForm = this.fb.group({
                
                seats: this.fb.array([
                  this.fb.group({
                userId:localStorage.getItem("userid"),
                seatId: ['', Validators.required],
                price: ['', Validators.required],
                quantity: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
                status:[1],
                flightId:[this.flightId]
                  })
                ])
              });
        this.seats = this.seatdetail;
              console.log(" SEATS =", this.seats);
              console.log("LENGTH = ",this.seats.length)
              if (this.seats && this.seats.length > 0) {
                this.myForm.patchValue({ seat: this.seats[0].seatId });
              }
              this.depDateTime = this.items.depDateTime.slice(0, 10);
              this.destDateTime = this.items.destDateTime.slice(0, 10);
              let formattedDepTime = this.datePipe.transform(
                this.items.depDateTime,
                "h:mm a"
              );
              this.depTime = formattedDepTime;
              let formattedDestTime = this.datePipe.transform(
                this.items.depDateTime,
                "h:mm a"
              );
              this.destTime = formattedDestTime;
            
            },
            error: (err: any) => {
              alert("Failed");
            },
            complete: () => {},
          });
         
        });
      }
      get seatsFormArray(): FormArray {
        return this.myForm.get('seats') as FormArray;
      }
      onChange(formArrayId: any, seatId: any) {
        console.log("Form array id = " + formArrayId + " and Seat Id =" + seatId);
        this.apiService.getSeatPrice(seatId, formArrayId).subscribe({
          next: (response: any) => {
            this.selectedSeatPrice = response;
            console.log("Price = ",this.selectedSeatPrice)
            const rowFormGroup = this.seatsFormArray.at(formArrayId) as FormGroup;
            rowFormGroup.patchValue({
              price: this.selectedSeatPrice,
            });
            const seatControl = this.seatsFormArray.controls[formArrayId];
    
            // seatControl.get("value")?.setValue(this.selectedSeatPrice);
            
          },
          error: (err: any) => {
            alert("failed");
          },
          complete: () => {},
        });
        this.apiService.getSeatNumber(seatId).subscribe({
          next:(response:any)=>{
            this.selectedSeatQuantity=response;
            console.log("SEAT QUANTITY = ",this.selectedSeatQuantity);
            const rowFormGroup=this.seatsFormArray.at(formArrayId)as FormGroup;
            const quantityControl=rowFormGroup.get('quantity');
            if(quantityControl){
              quantityControl.setValidators([Validators.required, Validators.pattern(/^[0-9]+$/), Validators.maxLength(10), Validators.max(this.selectedSeatQuantity)]);
              quantityControl.updateValueAndValidity();
            }
            
          }
        })
      }
      addOption() {
        this.seatsFormArray.push(this.fb.group({
          userId:localStorage.getItem("userid"),
          seatId: ['', Validators.required],
          price: [''],
          quantity: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
          status:[1],
          flightId:[this.flightId]
        }));
      }
    
      removeOption(index: number) {
        this.seatsFormArray.removeAt(index);
      }
    
      onSubmit() {
         
        
        
        console.log("Submitted Data = ",this.myForm.get('seats')?.value);
        this.apiService.addBooking(this.myForm.get('seats')?.value).subscribe({
          next:(response:any)=>{
           console.log("Succesfully Send Data")
          }
          })
      }
  // ngOnInit() {
  //   // Get Flight Data According to Flight Id - Start
  //   this.route.params.subscribe((params) => {
  //     const flightId = params["flightId"];
  //     this.flightId = flightId;
      
  //  // Call the API to get seatIds
  // this.apiService.getSeatId(this.flightId).toPromise().then((response: any) => {
  //   this.seatIds = response;
  //   console.log("SEAT IDs ARE :", this.seatIds);

  //   // Once seatIds is populated, create the form group
  //   this.myForm = this.fb.group({
  //     seats: this.fb.array([this.createSeatFormGroup()]),
  //   });
  // });
  //     this.apiService.getFlightDetail(flightId).subscribe({
  //       next: (response: any) => {
  //         this.items = response;
  //         this.seatList = this.items.seats;
  //         console.log(" SEAT DATA =", this.seatList);
  //         this.depDateTime = this.items.depDateTime.slice(0, 10);
  //         this.destDateTime = this.items.destDateTime.slice(0, 10);
  //         let formattedDepTime = this.datePipe.transform(
  //           this.items.depDateTime,
  //           "h:mm a"
  //         );
  //         this.depTime = formattedDepTime;
  //         let formattedDestTime = this.datePipe.transform(
  //           this.items.depDateTime,
  //           "h:mm a"
  //         );
  //         this.destTime = formattedDestTime;
  //         console.log("Flight Detail = " + this.items.flightId);
  //       },
  //       error: (err: any) => {
  //         alert("Failed");
  //       },
  //       complete: () => {},
  //     });
     
  //   });
  //   this.getRandom(); 
  // }
  
  

  
}
