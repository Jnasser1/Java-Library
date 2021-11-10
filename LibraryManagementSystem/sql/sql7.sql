SELECT B.title, BC.noOfCopies
FROM (((tbl_book as B inner join tbl_author as A on B.authId = A.authorId) inner join 
tbl_book_copies  as BC on B.bookId = BC.bookId) inner join 
tbl_library_branch as LB on BC.branchId = LB.branchId)
WHERE authorName='Stephen King' AND 
BranchName='Central'; 
