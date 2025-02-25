import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-applogo',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './applogo.component.html',
  styleUrl: './applogo.component.css'
})
export class ApplogoComponent implements OnInit{
  ngOnInit(): void {
    
  }

  

}
