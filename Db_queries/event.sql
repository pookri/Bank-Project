with emp_dates as
  2   ( select to_date('21/04/2011 21:12:12','dd/mm/yyyy hh24:mi:ss') date_from,
  3            to_date('17/09/2015 23:45:19','dd/mm/yyyy hh24:mi:ss') date_to
  4     from dual
  5   )
  6  select
  7    trunc(months_between(date_to,date_from)/12) years,
  8    mod(trunc(months_between(date_to,date_from)),12) mths,
  9    trunc(date_to - add_months(date_from,trunc(months_between(date_to,date_from)))) days,
 10    trunc(24*mod(date_to - date_from,1)) hrs,
 11    trunc(60*mod(24*mod(date_to - date_from,1),1)) mins,
 12    mod((date_to - date_from)*24*60*60,60) secs
 13  from emp_dates;