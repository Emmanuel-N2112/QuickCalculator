# Calendar

Simple java application that displays calendar according to user's locale, records and displays user's public
holidays.

## View Year

Receives a value of a year and country and displays all months, days and holidays in the year.

## View Month

Receives a value of a country, year, and month (number representing a month) and displays all days in the month and
its public holidays.

## View Today

Receives a value of a country and displays the month of current date with the current date highlighted and public
holidays within the current month.

## Go to date

Receives a value of a country and date in the format (yyyy-MM-dd or dd/MM/yyyy) and displays the month of the date
in highlight and public holidays within the month.

## View Holidays

Receives a value of a country and year and requests for a holiday rule;

- The holiday falls on the same month and date each year.
- If the actual holiday falls on a Saturday, provide a day off on the preceding Friday; if the holiday falls on a
  Sunday, then provide a day off on the Monday after the holiday.
- Or both of the above

**Note**: *An observed public* holiday is a day that is officially designated as a public holiday, and on which
many businesses and government offices are closed. However, if the public holiday falls on a weekend or another
non-working day, then the following working day may be designated as the observed public holiday. For example, if
Christmas Day falls on a Saturday, then the following Monday may be designated as the observed public holiday. This
allows people to have an extra day off work or school to celebrate the holiday or to engage in other activities.