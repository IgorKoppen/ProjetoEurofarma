import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BulkInsertDialogComponent } from './bulk-insert-dialog.component';

describe('BulkInsertDialogComponent', () => {
  let component: BulkInsertDialogComponent;
  let fixture: ComponentFixture<BulkInsertDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BulkInsertDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BulkInsertDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
