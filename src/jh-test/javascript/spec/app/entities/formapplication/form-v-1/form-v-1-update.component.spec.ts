/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FormV1UpdateComponent } from 'app/entities/formapplication/form-v-1/form-v-1-update.component';
import { FormV1Service } from 'app/entities/formapplication/form-v-1/form-v-1.service';
import { FormV1 } from 'app/shared/model/formapplication/form-v-1.model';

describe('Component Tests', () => {
    describe('FormV1 Management Update Component', () => {
        let comp: FormV1UpdateComponent;
        let fixture: ComponentFixture<FormV1UpdateComponent>;
        let service: FormV1Service;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FormV1UpdateComponent]
            })
                .overrideTemplate(FormV1UpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FormV1UpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FormV1Service);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FormV1('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.formV1 = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FormV1();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.formV1 = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
