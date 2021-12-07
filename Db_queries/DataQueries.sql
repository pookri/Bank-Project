INSERT INTO account_type VALUES ( 
      'SVNGS', 'SAVINGS');
      
INSERT INTO account_type VALUES ( 
      'CKNGS', 'CHECKING');
      
INSERT INTO account_type VALUES ( 
      'MM', 'MONEY_MARKET');
      
INSERT INTO account_type VALUES ( 
      'LN', 'LOAN');
      
INSERT INTO account_type VALUES ( 
      'BCHRG', 'CHARGE');

INSERT INTO TRANSACTION_TYPE VALUES('WD','Withdrawl',0.0);
INSERT INTO TRANSACTION_TYPE VALUES('DS','Customer Deposite',0.0);
INSERT INTO TRANSACTION_TYPE VALUES('SC','Service Charge',10.0);
INSERT INTO TRANSACTION_TYPE VALUES('IN','Interest',0.0);
INSERT INTO TRANSACTION_TYPE VALUES('LN','Loan Charge',0.0);
INSERT INTO TRANSACTION_TYPE VALUES('OD','Overdraft Charge',5.00);
INSERT INTO TRANSACTION_TYPE VALUES('ID','Initial Deposit',0.00);

INSERT INTO account VALUES ( 
      12453986, 'BARBO', 'SVNGS', 500.0, 3.2, SYSDATE); 
INSERT INTO account VALUES ( 
      7600893426,'BARBO','SVNGS',110000.0,2.1,SYSDATE-500);
INSERT INTO account VALUES ( 
      7600578621,'BARBO','SVNGS',120000.0,2.1,SYSDATE-300);
INSERT INTO account VALUES ( 
      7601564211,'BARBO','CKNGS',30000.0,0.0,SYSDATE-400);
INSERT INTO account VALUES ( 
      8604579231,'ORBT','SVNGS',206050.0,1.9,SYSDATE-100);
INSERT INTO account VALUES ( 
      8610542286,'ORBT','CKNGS',30000.0,0.0,SYSDATE-400);
INSERT INTO account VALUES ( 
      8003567112,'AXS1','SVNGS',30586.0,2.0,SYSDATE-50);
INSERT INTO account VALUES ( 
      8010865512,'AXS1','CKNGS',300000.0,0.0,SYSDATE-900);
INSERT INTO account VALUES ( 
      8012455367,'AXS2','SVNGS',90150.0,2.0,SYSDATE-200);
INSERT INTO account VALUES ( 
      8012965589,'AXS2','CKNGS',125000.0,0.0,SYSDATE-1000);
INSERT INTO account VALUES ( 
      8012988712,'AXS2','CKNGS',105000.0,0.0,SYSDATE-1500);




INSERT INTO customer VALUES ( 
      1579864322, 'Ramesh' , 'Sharma', 110, 72, 'SOUTH BRUNSWICK', 'NJ', 08822);

INSERT INTO customer VALUES ( 
      0178666959, 'Joe', 'Smith', 101, 71, 'NORTH BRUNSWICK', 'NJ', 08821);
      
INSERT INTO customer VALUES ( 
      1975612381, '	Carina' , 'Sharma', 100, 45, 'SEATTLE', 'WA', 08828);
      
INSERT INTO customer VALUES ( 
      1220364547, 'Gloria' , 'Shah', 125, 10, 'DALLAS', 'TX', 08801);
      
INSERT INTO customer VALUES ( 
      2449613350, 'Bob' , 'Patel', 189, 15, 'MIAMI', 'FL', 08818);
      
INSERT INTO customer VALUES ( 
      1100536981, 'Paul' , 'Peterson', 16, 25, 'BUFFALO', 'NY', 08829);


      
      
      
INSERT INTO BRANCH VALUES ( 
    'BARBO','BARODA', 258369147,120356100,250000,'41,CHERRY HILL','JERSERY','NJ',00826);
     
INSERT INTO BRANCH VALUES ( 
    'AXS1', 'AXS_ANAND',107823554,106257935,300000,'12,FRANKLIN PARK','BROOKLYN','NY',00827);

INSERT INTO BRANCH VALUES ( 
    'AXS2', 'AXS_SOMERSET',235984001,108369245,100000,'2,SOMERSET','CHICAGO','ILLINOIS',00822);
    
INSERT INTO BRANCH VALUES ( 
    'ORBT','ORBT_WOODBRIDGE',102236599,201536984,150000,'18,WOODBRIDGE','SOUTH JERSERY','NY',00823);


 
INSERT INTO employee VALUES ( 
      120356100, 'BARBO','Parul','Gandhi' , 7658423910, '12-MAY-2018');
      
INSERT INTO employee VALUES ( 
      258369147, 'BARBO','Rocky','Black' , 7954858999, '19-DEC-2017');      
INSERT INTO employee VALUES ( 
      120356100, 'BARBO','Parul','Gandhi' , 7658423910, '12-MAY-2018');
      
INSERT INTO employee VALUES ( 
      201536984, 'ORBT','Abhishek','Dutta', 7600987562, '2-DEC-2012');
     
INSERT INTO employee VALUES ( 
      102563948, 'AXS1','Naveen','Aedekar', 7651298321, '8-FEB-2015');

INSERT INTO employee VALUES ( 
      123405639, 'AXS2','Rahul','Rastogi', 7954198761, '16-JAN-2019');
      
INSERT INTO employee VALUES ( 
      148653223, 'BARBO','Avinash','Minha',8876532109, '21-JUN-2010');
      
INSERT INTO employee VALUES ( 
      138567994, 'BARBO','Chitresh','Barwe',7875189761, '9-JUL-2018');
      
INSERT INTO employee VALUES ( 
      134056987, 'AXS1','Parul','Sharma' , 7623910862, '12-AUG-2016');
      
INSERT INTO employee VALUES ( 
      106257935, 'AXS1','Shankar','Nair', 8734835910, '24-SEP-2009');
    
INSERT INTO employee VALUES ( 
      107823554, 'AXS1','Nisha','Damle', 8834995910, '4-NOV-2008');
      
INSERT INTO employee VALUES ( 
      108369245, 'AXS2','Amit','Borkar', 8876532109, '6-JAN-2009');
      
INSERT INTO employee VALUES ( 
      235984001, 'AXS2','Parul','Rastogi', 7954198761, '1-OCT-2009');
      
INSERT INTO employee VALUES ( 
      102236599, 'ORBT','Rosana','Patel', 7958296576, '18-FEB-2014');
      
INSERT INTO employee VALUES ( 
      145596312, 'ORBT','Elaine','Thakar', 7812469878, '1-SEP-2019');
      
INSERT INTO employee VALUES ( 
      199852236, 'ORBT','Shirley','Shah', 8894250016, '3-AUG-2018');
      
INSERT INTO employee VALUES ( 
      107785469, 'ORBT','Wynne','Baez', 7694832251, '1-JAN-2020');
      
INSERT INTO transaction VALUES(12453986,'WD',10.0,CURRENT_TIMESTAMP,SYSDATE,TRN_SC_NUM.nextval);      
commit;      
      
      
INSERT INTO employee_dependent VALUES(148653223,'Sahil');
INSERT INTO employee_dependent VALUES(148653223,'Keyur');
INSERT INTO employee_dependent VALUES(148653223,'James');
INSERT INTO employee_dependent VALUES(138567994,'Hitesh');
INSERT INTO employee_dependent VALUES(138567994,'Jason');
INSERT INTO employee_dependent VALUES(138567994,'Rimphal');

INSERT INTO ASSIST VALUES(138567994,1579864322);
INSERT INTO ASSIST VALUES(138567994,1975612381);

INSERT INTO customer_account VALUES(1579864322,12453986,SYSDATE);
INSERT INTO customer_account VALUES(1579864322,8610542286,SYSDATE-1600);
INSERT INTO customer_account VALUES(1975612381,8604579231,SYSDATE-500);

INSERT INTO LOAN VALUES(LOAN_SEQ.nextval,200000,8333.33,12453986,100000);
INSERT INTO LOAN VALUES(LOAN_SEQ.nextval,500000,10000.00,8604579231,400000);
INSERT INTO LOAN VALUES(LOAN_SEQ.nextval,100000,5000.00,12453986,45000);

DELETE FROM CUSTOMER_ACCOUNT WHERE cssn=1975612381;
DELETE FROM transaction WHERE transaction_id=147;
DELETE FROM BRANCH WHERE BRANCH_ID = 'BARBO' ;
DELETE FROM EMPLOYEE where employee_ssn = '120356100';
DELETE FROM EMPLOYEE where employee_ssn = '258369147';
DELETE FROM ACCOUNT WHERE account_number=12453986;

commit;

select * from Branch;

CREATE sequence seq_no start with 1000 increment by 50;