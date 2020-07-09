import { TestBed } from '@angular/core/testing';

import { NewsappService } from './newsapp.service';

describe('NewsappService', () => {
  let service: NewsappService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewsappService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
