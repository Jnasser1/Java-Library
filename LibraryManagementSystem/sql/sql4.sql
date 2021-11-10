select B.title, BR.name, BR.address
from  tbl_book B, tbl_book_loans BL, tbl_borrower BR,
tbl_library_branch LB
where LB.branchName= 'Sharpstown' 
and dueDate = '2021-10-30'
and BL.cardNo =  BR.cardNo
and LB.branchId = BL.branchId
and BL.bookId=B.bookId;
