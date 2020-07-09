import { AppPage } from './app.po';
import { browser, logging, by, element, protractor } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Sticky News');
  });

  it('should be redirected to /news/allnews route', ()=>{
    expect(browser.getCurrentUrl()).toContain('/news/allnews');
    browser.driver.sleep(1000);
  });
  it('should be redirected to /login route', ()=>{
    browser.element(by.css('.login-tab')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.driver.sleep(1000);
  });

  it('should be redirected to /register route', ()=>{
    browser.element(by.css('.register-tab')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(1000);
    
  });

  it('should be able to clear inputs',()=>{
    browser.element(by.id('firstName')).sendKeys("Goldie");
    browser.element(by.id('lastName')).sendKeys("Rose");
    browser.element(by.id('userid')).sendKeys("goldierosy10");
    browser.element(by.id('password')).sendKeys("123456");

    browser.element(by.css('.clear-user')).click();
    browser.sleep(10000);
    expect(browser.getCurrentUrl()).toContain('/register');
  });
  

  it('should be able to register user',()=>{
    browser.element(by.id('firstName')).sendKeys("Goldie");
    browser.element(by.id('lastName')).sendKeys("Rose");
    browser.element(by.id('userid')).sendKeys("goldierosy10");
    browser.element(by.id('password')).sendKeys("123456");

    browser.element(by.css('.register-user')).click();
    browser.sleep(10000);
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /login route agn', ()=>{
    browser.element(by.css('.login-tab')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
    browser.driver.sleep(1000);
  });

  it('should be able to login user',()=>{
    browser.element(by.id('userid')).sendKeys("goldierosy10");
    browser.element(by.id('password')).sendKeys("123456");
    browser.element(by.css('.login-user')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('news/allnews');
  });

  it('should be able to route to watchlist tab',()=>{
    browser.element(by.css('.watchlist-tab')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('news/watchlist');
  });

  it('should be able to route to search tab',()=>{
    browser.element(by.css('.search-tab')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('news/search');
  });

  it('should be able to search news from news api',()=>{
    browser.driver.manage().window().maximize();
    browser.element(by.id('search-button-input')).sendKeys("covid",protractor.Key.ENTER);
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('news/search');
  })

  it('should be able to route to all news tab',()=>{
    browser.element(by.css('.allnews-tab')).click();
    browser.sleep(5000);
    expect(browser.getCurrentUrl()).toContain('news/allnews');
  });

  it('should be able to view details',()=>{
  
    browser.element(by.css('.viewdetail')).click();
    browser.sleep(2000);
    browser.element(by.css('.close')).click();
    expect(browser.getCurrentUrl()).toContain('news/allnews')
  });

  it('should be able to add watchlist news',()=>{
    browser.driver.manage().window().maximize();
    browser.sleep(2000);
    browser.element(by.css('.addButton')).click();
  });
 
  it('navigate to watchlist tab',()=>{
    browser.element(by.css('.watchlist-tab')).click();
    browser.sleep(3000);
    expect(browser.getCurrentUrl()).toContain('news/watchlist');
  });

  it('should be able to view details of watchlist news',()=>{
    browser.element(by.css('.viewdetail')).click();
    browser.sleep(2000);
    browser.element(by.css('.close')).click();
    expect(browser.getCurrentUrl()).toContain('news/watchlist');
  });
 
  it('should be able to delete news from watchlist',()=>{
    browser.driver.manage().window().maximize();
    browser.sleep(2000);
    browser.element(by.css('.deleteButton')).click();
    browser.sleep(2000);
    //const searchItems=element.all(by.css('.movie-thumbnail'));
    //expect(searchItems.count()).toBe(0)
  
  });
  it('should be able to logout',()=>{
    browser.driver.manage().window().maximize();
    browser.sleep(5000);
    browser.element(by.css('.logout-tab')).click();
    expect(browser.getCurrentUrl()).toContain('login');
    browser.sleep(3000);
  });

  // afterEach(async () => {
  //   // Assert that there are no errors emitted from the browser
  //   const logs = await browser.manage().logs().get(logging.Type.BROWSER);
  //   expect(logs).not.toContain(jasmine.objectContaining({
  //     level: logging.Level.SEVERE,
  //   } as logging.Entry));
  // });
});
