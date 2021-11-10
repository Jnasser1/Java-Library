select name, address, COUNT(*)
from  tbl_borrower as BO
join tbl_book_loans as BL 
where BO.cardNo = BL.cardNo
group by BO.CardNo
having COUNT(*)>5;
