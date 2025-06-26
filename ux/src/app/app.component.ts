import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {User} from './models/user/user';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'ux';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  ngOnInit(): void {
    this.httpClient
      .get<User>("http://localhost:8222/api/users")
      .subscribe(data => console.log(data))
  }
}
