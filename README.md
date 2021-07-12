# CSVModifierUsingJavaStreamAPI

Modify an input CSV file of the format **Client,Date,Media Size,Country,Impressions,Clicks,Revenue**
VDX.tv,12/1/2020,160x600,United States,5,4,3
VDX.tv,12/1/2020,728x90,United States,6,7,8
VDX.tv,12/1/2020,160x600,Canada,5,1,12
VDX.tv,12/2/2020,300x250,United States,9,10,11
VDX.tv,12/2/2020,728x90,Canada,4,2,2
VDX.tv,12/1/2020,160x600,Canada,2,2,12

to a new CSV which has only Date Country and ,Impressions,Clicks,Revenue columns,

The ,Impressions,Clicks,Revenue would contain sum of ,Impressions,Clicks,Revenue for the Date and Country combination.

This is developed using Stream API with multiple group by operations

This is the new CSV:

**Date,Country,Impressions,Clicks,Revenue**
12/1/2020,Canada,7,3,24.0
12/1/2020,United States,11,11,11.0
12/2/2020,Canada,4,2,2.0
12/2/2020,United States,9,10,11.0


