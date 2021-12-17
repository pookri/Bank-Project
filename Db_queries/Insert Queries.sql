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

commit;


INSERT INTO account VALUES ( 
      7600893426,'BARBO','SVNGS',110000.0,2.1);

INSERT INTO account VALUES ( 
      8604579231,'ORBT','SVNGS',206050.0,1.9);

INSERT INTO account VALUES ( 
      8003567112,'AXS1','SVNGS',30586.0,2.0);

INSERT INTO account VALUES ( 
      8012455367,'AXS2','SVNGS',90150.0,2.0);

select * from Branch;

CREATE sequence seq_no start with 1000 increment by 50;


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


commit;
      
SELECT * FROM BRANCH;

select * from account;
 
 ROLLBACK;
      
INSERT INTO employee VALUES ( 
      120356100, 'BARBO','Parul','Gandhi' , 7658423910, '12-MAY-2018');
      
INSERT INTO employee VALUES ( 
      258369147, 'BARBO','Rocky','Black' , 7954858999, '19-DEC-2017');
INSERT INTO BRANCH VALUES ( 
    'BARBO', 258369147,'BARODA','41,CHERRY HILL','JERSERY',120356100,'NJ',00826,100000);
      
commit;

INSERT INTO BRANCH VALUES ( 
    'AXS1', 107823554,'AXS_ANAND','12,FRANKLIN PARK','BROOKLYN',106257935,'NY',00827);

INSERT INTO BRANCH VALUES ( 
    'AXS2', 235984001,'AXS_SOMERSET','2,SOMERSET','CHICAGO',108369245,'ILLINOIS',00822);
    
INSERT INTO BRANCH VALUES ( 
    'ORBT',  102236599,'ORBT_WOODBRIDGE','18,WOODBRIDGE','SOUTH JERSERY',201536984,'NY',00823);

DELETE FROM BRANCH WHERE BRANCH_ID = 'AXS1' ;

DELETE FROM transaction WHERE transaction_account_number=1400;
DELETE FROM BRANCH WHERE BRANCH_ID = 'BARBO' ;
DELETE FROM EMPLOYEE where employee_ssn = '120356100';
DELETE FROM EMPLOYEE where employee_ssn = '258369147';
DELETE FROM ACCOUNT WHERE account_number=1405;
commit;



INSERT INTO account VALUES ( 
      12453986, 'BARBO', 'SVNGS', 1.0, 3.2, SYSDATE);
      
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