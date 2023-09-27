#version 330

// Input the texture coordinates, name must match
in vec4 vertColour;

out vec4 fragColour;

void main() {
    fragColour = vertColour;
}
