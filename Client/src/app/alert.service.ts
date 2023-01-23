import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
@Injectable({
  providedIn: 'root'
})
export class AlertService {
  
  constructor(private toastr: ToastrService) {
  }
  /**
   * Method to show success message
   * @param message
   * @param title
   */
  
  
  showSuccess(message: string | undefined, title: string | undefined) {
    
    this.toastr.success(message, title ,{timeOut:1000});
  }

  /**
   * Method to show error message
   * @param message
   * @param title
   */
  showError(message: string | undefined, title: string | undefined) {
    this.toastr.error(message, title,{timeOut:1000});
  }

  /**
   * Method to show info message
   * @param message
   * @param title
   */
  showInfo(message: string | undefined, title: string | undefined) {
    this.toastr.info(message, title,{timeOut:1000});
  }

  /**
   * Method to show warning message
   * @param message
   * @param title
   */
  showWarning(message: string | undefined, title: string | undefined) {
    this.toastr.warning(message, title);
  }
}
