import { TestBed } from '@angular/core/testing';

import { ObjectScanService } from './object-scan.service';

describe('ObjectScanService', () => {
  let service: ObjectScanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObjectScanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
