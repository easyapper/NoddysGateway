import {
    Component,
    ViewChild,
    ElementRef,
    Renderer2,
    AfterViewInit
} from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit {

    rotatingTexts = ['adventure', 'astronaut camp', 'chess match', 'cook-off', 'dance recital', 'puppet show', 'storytime', 'swim lesson', 'swordfight']

    @ViewChild('rotatingText') rotatingText: ElementRef;

    eventName = '';
    dates = [{
            value: 'All Dates',
            viewValue: 'All Dates'
        },
        {
            value: 'Today',
            viewValue: 'Today'
        },
        {
            value: 'Tomorrow',
            viewValue: 'Tomorrow'
        },
        {
            value: 'This Week',
            viewValue: 'This Week'
        },
        {
            value: 'This Weekend',
            viewValue: 'This Weekend'
        },
        {
            value: 'Next Week',
            viewValue: 'Next Week'
        },
        {
            value: 'Next Month',
            viewValue: 'Next Month'
        }
    ];
    eventDate = this.dates[0].viewValue;

    constructor(private renderer: Renderer2, private router: Router) {}

    ngAfterViewInit() {
        let i = 0;
        setInterval(() => {
            this.renderer.setProperty(this.rotatingText.nativeElement, 'innerHTML', this.rotatingTexts[i]);
            i++;
            if (i == this.rotatingTexts.length)
                i = 0;
        }, 500)
    }

    displayActivities(){
        this.router.navigateByUrl('/activities');
    }
}
