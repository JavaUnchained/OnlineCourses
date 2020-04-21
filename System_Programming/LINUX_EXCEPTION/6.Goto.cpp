#include <stdexcept>
#include <iostream>

int main(int argc, char *argv[]) {
    try {
        try {
            try {
                goto out;
            } catch (const std::length_error& exception) {
                std::cout << "Length error exception ocurred." << std::endl;
            }
        } catch (const std::out_of_range& exception) {
            std::cout << "Out of range exception ocurred." << std::endl;
        }
    } catch (const std::overflow_error& exception) {
        std::cout << "Overflow exception ocurred." << std::endl;
    }

    return 0x0;
out:
    std::cout << "Goto instruction worked." << std::endl;
    return 0x0;
}
