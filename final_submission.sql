USE [P0014]
GO
INSERT [dbo].[tbl_Role] ([ID], [name], [description]) VALUES (1, N'admin', N'')
INSERT [dbo].[tbl_Role] ([ID], [name], [description]) VALUES (2, N'student', NULL)
GO
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [roleID], [status]) VALUES (N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'admin cute', 1, 1)
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [roleID], [status]) VALUES (N'admin@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'admin xau trai', 2, 1)
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [roleID], [status]) VALUES (N'student@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'tien 213', 2, 1)
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [roleID], [status]) VALUES (N'tien@gmail.com', N'15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', N'Tien Sad', 2, 1)
INSERT [dbo].[tbl_User] ([email], [password], [fullname], [roleID], [status]) VALUES (N'user', N'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', N'user cute', 2, 1)
GO
INSERT [dbo].[tbl_Subject] ([ID], [name]) VALUES (N'1', N'java')
INSERT [dbo].[tbl_Subject] ([ID], [name]) VALUES (N'2', N'C#')
INSERT [dbo].[tbl_Subject] ([ID], [name]) VALUES (N'3', N'japanese')
GO
SET IDENTITY_INSERT [dbo].[tbl_Question] ON 

INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1, N'1', N'admin', 1, 1611533686502, N'thay khanh co cute ko', N'yes', N'rat cute nha', N'yes yes', N'tat nhien roi nha', 3)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (2, N'2', N'admin', 1, 1611534003540, N'ban hoc tieng nhat ai', N'tue', N'thay tue', N'tue cute', N'tue gau truc', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (3, N'2', N'admin', 1, 1611535524802, N'ban hoc java web ai?', N'khanh dai ca', N'thay khanh kute', N'ko ai ngoai dai ca khanh', N'khanh kt haha', 2)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (4, N'1', N'admin', 0, 1611536129556, N'ai la nguoi sang lap ra FPT', N'truong gia binh', N'kieu trong khanh', N'pham nhat vuong', N'nguyen the hoang', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (5, N'1', N'admin', 0, 1611542819760, N'mot tuan co bao nhieu ngay', N'0', N'2', N'5', N'7', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (6, N'3', N'admin', 0, 1611559898143, N'a', N'b', N'd', N'd', N'bbb', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (7, N'3', N'admin', 0, 1611565125683, N'truong fpt nam o quan may', N'1', N'2', N'3', N'9', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1012, N'1', N'admin', 1, 1611815457571, N'lam sao de co nguoi yeu ?', N'di cua gai', N'de cai cua', N'kiem that nhieu tien', N'ai ma biet', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1015, N'1', N'user', 1, 1611848946114, N'asd', N'as', N'asd', N'asd', N'asd', 3)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1019, N'1', N'admin', 1, 1612122592635, N'123', N'456', N'123', N'123', N'123', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1020, N'3', N'admin', 1, 1612351192617, N'nguyet thuc toan phan xay ra khi nao', N'trai dat che mat troi', N'mat trang che mat troi', N'trai dat che mat trang', N'dap an khac', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1021, N'3', N'admin', 1, 1612351240350, N'ban thuong lam gi khi ranh', N'ngu', N'code', N'da banh', N'ko lam j het', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1022, N'2', N'admin', 1, 1612351271672, N'may tinh cua ban mua bao nhieu tien', N'10 cu', N'15 cu', N'20 cu', N'khong muon noi', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1023, N'2', N'admin', 1, 1612351306490, N'ban muon co nguoi yeu ko', N'co', N'khong', N'ko them', N'rat muon', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1024, N'2', N'admin', 1, 1612351348509, N'ban thuong an sang mon gi', N'khong bao gio an sang', N'com tam', N'banh mi', N'uong nuoc thay com', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1025, N'1', N'admin', 1, 1612351406966, N'ai la dai ca trong lop lab cua ban', N'khanh kk', N'dai ca khanh', N'khanh kt', N'thay khanh kute hihi', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1026, N'1', N'admin', 1, 1612351485592, N'neu rot lab web ban se lam j', N'nhay cau tu tu', N've nha om gau khoc', N'lam lai tu dau, ko nan chi', N'huhu ko biet', 3)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1027, N'1', N'admin', 1, 1612351535578, N' neu co 1 dieu uoc ban se uoc j', N'uoc dc ra truong', N'uoc pass mon lab cua thay', N'uoc dc pro nhu thay', N'uoc co them 10 dieu uoc nua', 4)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1028, N'1', N'admin', 1, 1612351620687, N'an tet xong ban con nho'' j ko', N'ko', N'quen het roi', N'nho chut chut', N'ko quen j het', 1)
INSERT [dbo].[tbl_Question] ([ID], [subjectID], [adminEmail], [status], [createDate], [questionContent], [answerContent1], [answerContent2], [answerContent3], [answerContent4], [correctAnswer]) VALUES (1029, N'2', N'admin', 1, 1612351729730, N'ai la nguoi giau nhat the gioi', N'Jeff Bezos', N'Bill Gates', N'Mark Zuckerberg', N'tui', 4)
SET IDENTITY_INSERT [dbo].[tbl_Question] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_Test] ON 

INSERT [dbo].[tbl_Test] ([ID], [name], [numOfQuestion], [createDate], [adminEmail], [subjectID], [timeLength], [avaiableLength]) VALUES (1, N'test1', 10, 123123123, N'admin', N'1', 12, 123)
INSERT [dbo].[tbl_Test] ([ID], [name], [numOfQuestion], [createDate], [adminEmail], [subjectID], [timeLength], [avaiableLength]) VALUES (2, N'test 2', 2, 1612334024578, N'admin', N'1', 12, 123)
SET IDENTITY_INSERT [dbo].[tbl_Test] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_StudentTest] ON 

INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (1, N'user', 2, 1612343846383, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (2, N'user', 2, 1612344289184, 0, 2)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (3, N'user', 2, 1612344415733, 0, 2)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (4, N'user', 2, 1612344428319, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (5, N'user', 2, 1612346219762, 0, 2)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (6, N'user', 2, 1612346403454, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (7, N'user', 2, 1612346510341, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (8, N'user', 2, 1612346795806, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (9, N'user', 2, 1612346891903, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (10, N'user', 2, 1612346913301, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (11, N'user', 2, 1612347027746, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (12, N'user', 2, 1612347282444, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (13, N'user', 2, 1612347349768, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (14, N'user', 2, 1612347393836, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (15, N'user', 2, 1612347395897, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (16, N'user', 2, 1612347416761, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (17, N'user', 2, 1612347421211, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (18, N'user', 2, 1612347422835, 0, 1)
INSERT [dbo].[tbl_StudentTest] ([ID], [studentEmail], [testID], [attempDate], [correctAnswerNumber], [studentChoice]) VALUES (19, N'user', 2, 1612347528132, 1, 4)
SET IDENTITY_INSERT [dbo].[tbl_StudentTest] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_TestQuestion] ON 

INSERT [dbo].[tbl_TestQuestion] ([ID], [testID], [questionID]) VALUES (1, 2, 4)
INSERT [dbo].[tbl_TestQuestion] ([ID], [testID], [questionID]) VALUES (2, 2, 5)
SET IDENTITY_INSERT [dbo].[tbl_TestQuestion] OFF
GO
