select BR.name
from  tbl_borrower as BR
where not exists (
    select * from  tbl_book_loans as BL
    where BR.cardNo = BL.cardNo
);
