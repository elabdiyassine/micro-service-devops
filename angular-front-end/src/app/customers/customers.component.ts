// customers.component.ts

import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers() {
    this.http.get<any[]>('http://localhost:8081/customers')
      .subscribe((response) => {
        this.customers = response;
      }, (error) => {
        console.error('Error fetching customers:', error);
      });
  }

  openEditModal(customer: any) {
    // Populate the edit modal with customer data and show the modal
    // You can implement this logic as needed
    $('#editCustomerModal').modal('show');
  }

  deleteCustomer(customerId: number) {
    // Implement logic to delete the customer with the given ID
    // You can make an HTTP request to your backend API to delete the customer
    console.log('Deleting customer with ID:', customerId);
  }
}
