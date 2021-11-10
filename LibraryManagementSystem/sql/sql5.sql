select LB.branchName, COUNT(*)
from tbl_book_copies BC, tbl_library_branch LB 
where BC.branchId = LB.branchId
group by LB.branchName;
