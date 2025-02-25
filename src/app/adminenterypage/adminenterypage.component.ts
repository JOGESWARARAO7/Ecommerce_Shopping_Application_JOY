import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-adminenterypage',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  templateUrl: './adminenterypage.component.html',
  styleUrl: './adminenterypage.component.css'
})
export class AdminenterypageComponent {

}
