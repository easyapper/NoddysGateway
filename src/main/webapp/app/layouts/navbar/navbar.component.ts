import {
    Component,
    OnInit
} from '@angular/core';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    cities = [{
            value: 'Delhi',
            viewValue: 'Delhi'
        },
        {
            value: 'Mumbai',
            viewValue: 'Mumbai'
        },
        {
            value: 'Kolkata',
            viewValue: 'Kolkata'
        },
        {
            value: 'Chennai',
            viewValue: 'Chennai'
        },
        {
            value: 'Bangalore',
            viewValue: 'Bangalore'
        }
    ];

    constructor() {}

    ngOnInit() {}

}
