import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresenceListDialogComponent } from './presence-list-dialog.component';

describe('PresenceListDialogComponent', () => {
  let component: PresenceListDialogComponent;
  let fixture: ComponentFixture<PresenceListDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PresenceListDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PresenceListDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
