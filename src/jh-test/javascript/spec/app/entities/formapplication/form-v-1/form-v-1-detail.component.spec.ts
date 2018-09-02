/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FormV1DetailComponent } from 'app/entities/formapplication/form-v-1/form-v-1-detail.component';
import { FormV1 } from 'app/shared/model/formapplication/form-v-1.model';

describe('Component Tests', () => {
    describe('FormV1 Management Detail Component', () => {
        let comp: FormV1DetailComponent;
        let fixture: ComponentFixture<FormV1DetailComponent>;
        const route = ({ data: of({ formV1: new FormV1('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FormV1DetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FormV1DetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FormV1DetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.formV1).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
