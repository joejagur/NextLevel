
import {ChangeDetectionStrategy,inject, Component} from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'add-add-game-pop-up',
  templateUrl: './add-game-pop-up.component.html',
  styleUrls: ['./add-game-pop-up.component.css']
})
export class AddGamePopUpComponent {

dialogRef = inject(MatDialogRef<AddGamePopUpComponent>);

ratings : string[] = ["10 - Masterpiece", "9 - Amazing", "8 - Great", "7 - Good", "6 - Fair", "5 - Okay", "4 - Mediocre", "3 - Bad", "2 - Terrible", "1 - Abysmal"]

closeWithoutSave() {
  this.dialogRef.close();
}
submit() {
throw new Error('Method not implemented.');
}

}
