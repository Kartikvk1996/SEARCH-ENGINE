#include "./util.hpp"

string ushort2str(ushort i) {
	char buf[16];
	sprintf(buf, "%d", i);
	return string(buf);
}
