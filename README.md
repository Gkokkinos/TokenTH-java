## Design

### *Iteration*

As written in comments:

* Iterate through the intervals list, get the first element and the new added element (last in list) and check if we should merge them
* If merging is decided, remove the two elements and add the new one.
* Then we call this  function in recursion until no merging is furthermore required

### *Merging*
I started thinking of interval merging with the following logic:
Based on a function that accepts two intervals ->
1. check for boundary equalities:
```
if (currentInterval.start == nextInterval.start || currentInterval.end == nextInterval.end || currentInterval.end == nextInterval.start ||  currentInterval.start == nextInterval.end) {
        return true;
    }
```
2. check for intervals inside other interval:
if ((currentInterval.start > nextInterval.start && currentInterval.end < nextInterval.end) || (nextInterval.start > currentInterval.start && nextInterval.end < currentInterval.end)){
        return true;

3. check for intervals that intercet in one boundary only:
```
if ((currentInterval.end <= nextInterval.end && currentInterval.end >= nextInterval.start) || (nextInterval.end <= currentInterval.end && nextInterval.end >= currentInterval.start)) {
            return true;
        }
```		
Then I figured out that the case 3. covers every case, and it can be re written better without checkign for both intervals:
```
 //Checks if intervals are overlapping e.g. [0,5]-[5,6] OR [10,20]-[13,17] OR [1,10]-[7,12] .etc
  overlaps(other: Interval): boolean {
    return this.start <= other.end && other.start <= this.end;
  } 
```

Adjecency is being checked in diff function -> ` adjacent(other: Interval): boolean`

basically checking the boundaries with +1 added to them.

### *Bonus*

only implemented 2 simple cases as I was running out of time (same interval and example given interval -> split in two)

## *Unit tests*

Used JUnit` for the tests - easily runnable from moder IDE



