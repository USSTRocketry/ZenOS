from setuptools import setup

setup(
    name='example',
    version='1.0',
    py_modules=['example'],
    install_requires=[
        'PyQt5',
        'pyqtgraph',
        'numpy',
    ],
    entry_points='''
        [console_scripts]
        example_qt=example:main
    ''',
)
