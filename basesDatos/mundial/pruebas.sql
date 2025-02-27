Select * from country c
		inner join ismember i on c.Code = i.Country
		inner join organization o on i.Organization = "ACC" group by c.name;
        
        Select * from country c
								inner join ismember i on c.Code = i.Country
								where i.Organization = "ACC"
						group by c.name;
                        
select * from Organization;
select * from ismember;


Insert into Organization 
										(Abbreviation, Name, City, Established)
								values
										("abc", "ahora", "lugo", "12-12-2012");

Insert into ismember 
										(Country, Organization, Type)
								values
										("Spain", "abc", "member");
                                        
select * from ismember where Organization = "abc";
Delete from ismember where organization = "abc";
Insert into ismember 
										(Country, Organization, Type)
								values
										("askjd", "abc", "member");
                                        
Alter table OrderDetails
Add total double as (unitPrice * quantity * (1 - discount));

select * from customers c where c.Country = "Spain";

