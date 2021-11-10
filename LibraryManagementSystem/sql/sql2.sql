select branchName, noOfCopies
from  tbl_book B,  tbl_book_copies BC, tbl_library_branch LB
where B.bookId=BC.bookId
and BC.branchId=LB.branchId
and title = 'The Lost Tribe';
