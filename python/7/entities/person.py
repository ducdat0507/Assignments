import datetime


class Person():
    def __init__(self, *, first_name, middle_name, last_name, birthday, address, phone):
        self.first_name = first_name
        self.middle_name = middle_name
        self.last_name = last_name
        self.birthday = birthday
        self.address = address
        self.phone = phone

    @property
    def first_name(self):
        return self._first_name
    @first_name.setter
    def first_name(self, value):
        self._first_name = str(value)

    @property
    def middle_name(self):
        return self._middle_name
    @middle_name.setter
    def middle_name(self, value):
        self._middle_name = str(value)

    @property
    def last_name(self):
        return self._last_name
    @last_name.setter
    def last_name(self, value):
        self._last_name = str(value)

    @property
    def full_name(self):
        return " ".join([x for x in [self.first_name, self.middle_name, self.last_name] if x])

    @property
    def birthday(self):
        return self._birthday
    @birthday.setter
    def birthday(self, value):
        if not isinstance(value, datetime.date):
            raise ValueError("birthday must be a date")
        self._birthday = value

    @property
    def address(self):
        return self._address
    @address.setter
    def address(self, value):
        self._address = str(value)

    @property
    def phone(self):
        return self._phone
    @phone.setter
    def phone(self, value):
        self._phone = str(value)