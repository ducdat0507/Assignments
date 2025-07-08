"use strict";
var __classPrivateFieldGet = (this && this.__classPrivateFieldGet) || function (receiver, state, kind, f) {
    if (kind === "a" && !f) throw new TypeError("Private accessor was defined without a getter");
    if (typeof state === "function" ? receiver !== state || !f : !state.has(receiver)) throw new TypeError("Cannot read private member from an object whose class did not declare it");
    return kind === "m" ? f : kind === "a" ? f.call(receiver) : f ? f.value : state.get(receiver);
};
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
var _ConsoleLogger_instances, _ConsoleLogger_getCurrentDateString;
Object.defineProperty(exports, "__esModule", { value: true });
exports.ConsoleLogger = void 0;
var ConsoleLogger = /** @class */ (function () {
    function ConsoleLogger() {
        _ConsoleLogger_instances.add(this);
    }
    ConsoleLogger.prototype.info = function (context) {
        var items = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            items[_i - 1] = arguments[_i];
        }
        console.log.apply(console, __spreadArray(["[ INFO] [".concat(__classPrivateFieldGet(this, _ConsoleLogger_instances, "m", _ConsoleLogger_getCurrentDateString).call(this), "] [").concat(context, "] :\n\t")], items, false));
    };
    ConsoleLogger.prototype.warn = function (context) {
        var items = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            items[_i - 1] = arguments[_i];
        }
        console.warn.apply(console, __spreadArray(["[ WARN] [".concat(__classPrivateFieldGet(this, _ConsoleLogger_instances, "m", _ConsoleLogger_getCurrentDateString).call(this), "] [").concat(context, "] :\n\t")], items, false));
    };
    ConsoleLogger.prototype.error = function (context) {
        var items = [];
        for (var _i = 1; _i < arguments.length; _i++) {
            items[_i - 1] = arguments[_i];
        }
        console.error.apply(console, __spreadArray(["[ERROR] [".concat(__classPrivateFieldGet(this, _ConsoleLogger_instances, "m", _ConsoleLogger_getCurrentDateString).call(this), "] [").concat(context, "] :\n\t")], items, false));
    };
    return ConsoleLogger;
}());
exports.ConsoleLogger = ConsoleLogger;
_ConsoleLogger_instances = new WeakSet(), _ConsoleLogger_getCurrentDateString = function _ConsoleLogger_getCurrentDateString() {
    var date = new Date();
    return date.getUTCFullYear()
        + (date.getUTCMonth() + 1 + "").padStart(2, "0")
        + (date.getUTCDate() + "").padStart(2, "0")
        + " "
        + (date.getUTCHours() + "").padStart(2, "0")
        + (date.getUTCMinutes() + "").padStart(2, "0")
        + (date.getUTCSeconds() + "").padStart(2, "0");
};
