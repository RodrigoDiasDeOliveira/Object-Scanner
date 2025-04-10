import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UploadImageComponent } from './components/upload-image/upload-image.component';

@NgModule({
  declarations: [
    AppComponent,
    UploadImageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],cd..
  
  bootstrap: [AppComponent]
})
export class AppModule { }
