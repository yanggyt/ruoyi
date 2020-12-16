
DROP table if Exists kid_score ;
create table kid_score(
id tinyint unsigned not null,
birth_day date not null,
score int unsigned not null,
primary key(id, birth_day)  -- 唯一索引是由 id + birth_day 两个字段组成
) engine = InnoDB;
-- 初始化数据
insert into kid_score(id, birth_day, score) values (1,'2019-01-15',10),(2,'2019-01-16',20);


-- 如果你插入的记录导致一个UNIQUE索引或者primary key(主键)出现重复，那么就会认为该条记录存在，
-- 则执行update语句而不是insert语句，
-- 反之，则执行insert语句而不是更新语句。


-- 1. 唯一索引重复
insert into kid_score(id, birth_day, score) 
values (1,'2019-01-15',30) ON DUPLICATE KEY UPDATE score = score + 50;


-- 2. 唯一索引不重复
insert into kid_score(id, birth_day, score) 
values (2,'2019-01-15',30) ON DUPLICATE KEY UPDATE score = score + 50;



-- 3. 唯一索引重复，插入完全相同数据
insert into kid_score(id, birth_day, score) 
values (2,'2019-01-16',20) ON DUPLICATE KEY UPDATE score = 20;


-- 4. 影响行数
-- 需要注意的是：如果行作为新记录被插入，则受影响行的值为1；如果原有的记录被更新，则受影响行的值为2，
-- 如果更新的数据和已有的数据一模一样，则受影响的行数是0。



Select * From kid_score ;




