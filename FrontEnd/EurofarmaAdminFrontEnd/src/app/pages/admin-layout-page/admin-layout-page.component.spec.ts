import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLayoutPageComponent } from './admin-layout-page.component';

describe('AdminLayoutPageComponent', () => {
  let component: AdminLayoutPageComponent;
  let fixture: ComponentFixture<AdminLayoutPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminLayoutPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminLayoutPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
