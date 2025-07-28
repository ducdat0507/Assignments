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
    def set_first_name(self, value):
        self._first_name = str(value)

    @property
    def middle_name(self):
        return self._middle_name
    @middle_name.setter
    def set_middle_name(self, value):
        self._middle_name = str(value)

    @property
    def last_name(self):
        return self._last_name
    @last_name.setter
    def set_last_name(self, value):
        self._last_name = str(value)

    @property
    def birthday(self):
        return self._birthday
    @birthday.setter
    def set_birthday(self, value):
        self._birthday = str(value)

    @property
    def address(self):
        return self._address
    @address.setter
    def set_address(self, value):
        self._address = str(value)

    @property
    def phone(self):
        return self._phone
    @phone.setter
    def set_phone(self, value):
        self._phone = str(value)