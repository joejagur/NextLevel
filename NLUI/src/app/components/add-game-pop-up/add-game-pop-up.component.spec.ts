import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGamePopUpComponent } from './add-game-pop-up.component';

describe('AddGamePopUpComponent', () => {
  let component: AddGamePopUpComponent;
  let fixture: ComponentFixture<AddGamePopUpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddGamePopUpComponent]
    });
    fixture = TestBed.createComponent(AddGamePopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
