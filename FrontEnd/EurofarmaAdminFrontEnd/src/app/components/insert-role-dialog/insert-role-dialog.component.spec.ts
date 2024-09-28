import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertRoleDialogComponent } from './insert-role-dialog.component';

describe('InsertRoleDialogComponent', () => {
  let component: InsertRoleDialogComponent;
  let fixture: ComponentFixture<InsertRoleDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertRoleDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsertRoleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
