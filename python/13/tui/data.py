
class Vec2():
    x: float
    y: float

    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y
    
    def __ne__(self, other):
        return not self == other

class FlexVec2():
    abs_x: float
    abs_y: float
    rel_x: float
    rel_y: float

    def __init__(self, abs_x: float, abs_y: float, rel_x: float, rel_y: float):
        self.abs_x = abs_x
        self.abs_y = abs_y
        self.rel_x = rel_x
        self.rel_y = rel_y

    def __eq__(self, other):
        return (self.abs_x == other.abs_x and self.abs_y == other.abs_y
            and self.rel_x == other.rel_x and self.rel_y == other.rel_y)
    
    def __ne__(self, other):
        return not self == other

class Rect():
    left: int
    top: int
    width: int
    height: int
    
    def __init__(self, left: int, top: int, width: int, height: int):
        self.left = left
        self.top = top
        self.width = width
        self.height = height

    def derive(self, position: FlexVec2, size: FlexVec2, anchor: Vec2):
        width = size.abs_x + size.rel_x * self.width
        height = size.abs_y + size.rel_y * self.height
        left = self.left + position.abs_x + position.rel_x * self.width - anchor.x * width
        top = self.top + position.abs_y + position.rel_y * self.height - anchor.y * height
        return Rect(int(left), int(top), int(width), int(height))

    def __eq__(self, other):
        return (self.top == other.top and self.left == other.left
            and self.width == other.width and self.height == other.height)
    
    def __ne__(self, other):
        return not self == other
